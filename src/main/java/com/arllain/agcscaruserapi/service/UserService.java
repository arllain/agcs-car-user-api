package com.arllain.agcscaruserapi.service;

import com.arllain.agcscaruserapi.domain.User;
import com.arllain.agcscaruserapi.dto.UserCredentialsDTO;
import com.arllain.agcscaruserapi.dto.UserDataDTO;
import com.arllain.agcscaruserapi.service.exception.AuthenticationCustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.arllain.agcscaruserapi.repository.UserRepository;
import com.arllain.agcscaruserapi.security.JwtTokenProvider;
import com.arllain.agcscaruserapi.service.exception.ObjectFoundException;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.List;

/**
 * @author arllain
 *
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ModelMapper modelMapper;


    /**
     * @param userSignin
     * @return
     */
    public UserCredentialsDTO signIn(User userSignin) {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(userSignin.getLogin(),
                            userSignin.getPassword()));

            com.arllain.agcscaruserapi.domain.User savedUser = userRepository.findByLogin(userSignin.getLogin());
            savedUser.setLast_login(Instant.now());
            userRepository.save(savedUser);

            String token = jwtTokenProvider.createToken(userSignin.getLogin());
            UserDataDTO user = modelMapper.map(savedUser, UserDataDTO.class);
            UserCredentialsDTO credential = new UserCredentialsDTO(user, token);
            return credential;
        } catch (AuthenticationException e) {
            throw new AuthenticationCustomException("Invalid login or password");
        }
    }

    /**
     *
     * @return List<User>
     */
    public List<User> listAll() {
        return userRepository.findAll();
    }

    /**
     * @param id
     * @return User
     */
    public User findById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ObjectFoundException("User not found"));
    }

    /**
     * @param user
     * @return User
     */
    @Transactional
    public String save(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ObjectFoundException("E-mail already exists");
        }

        if (userRepository.existsByLogin(user.getLogin())) {
            throw new ObjectFoundException("Login already exists");
        }

        user.setCreated_at(Instant.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getCars() != null) {
            user.getCars().stream().forEach(car -> car.setUser(user));
        }

        userRepository.save(user);

        return user.getId().toString();
    }

    /**
     *
     * @param id
     */
    public void delete(long id) {
        userRepository.delete(findById(id));
    }

    /**
     *
     * @param id
     * @param user
     */
    public void update(long id, User user) {
        User savedUser = findById(id);
        user.setId(savedUser.getId());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreated_at(savedUser.getCreated_at());
        user.setLast_login(savedUser.getLast_login());
        userRepository.save(user);
    }

    /**
     * @param req
     * @return
     */
    public User whoami(HttpServletRequest req) {
        try {
            return userRepository.findByLogin(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
        } catch (AuthenticationException e) {
            throw new AuthenticationCustomException("Invalid login or password");
        }
    }
}
