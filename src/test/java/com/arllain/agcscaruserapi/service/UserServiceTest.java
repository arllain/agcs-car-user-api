package com.arllain.agcscaruserapi.service;

import com.arllain.agcscaruserapi.domain.User;
import com.arllain.agcscaruserapi.repository.UserRepository;
import com.arllain.agcscaruserapi.security.JwtTokenProvider;
import com.arllain.agcscaruserapi.service.exception.ObjectFoundException;
import com.arllain.agcscaruserapi.service.exception.ObjectNotFoundException;
import com.arllain.agcscaruserapi.utils.CartCreator;
import com.arllain.agcscaruserapi.utils.UserCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private PasswordEncoder passwordEncoderMock;

    @Mock
    private JwtTokenProvider jwtTokenProviderMock;

    @Mock
    private AuthenticationManager authenticationManagerMock;

    @Mock
    private ModelMapper modelMapperMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        BDDMockito.when(userRepositoryMock.findAll()).thenReturn(List.of(UserCreator.createValidUser()));
        BDDMockito.when(userRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(UserCreator.createValidUser()));
        BDDMockito.when(userRepositoryMock.save(ArgumentMatchers.any(User.class))).thenReturn(UserCreator.createValidUser());
    }


//    @Test
//    void signIn() {
//    }

    @Test
    @DisplayName("Returns a list of users when succesful")
    void listAll() {
        List<User> userList = userService.listAll();
        Assertions.assertThat(userList)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
    }

    @Test
    @DisplayName("findById returns a user")
    void findById() {
        Long expectedId = UserCreator.createValidUser().getId();

        User user = userService.findById(1);

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getId()).isNotNull().isEqualTo(expectedId);

    }

    @Test
    @DisplayName("findById throws ObjectNotFoundException when user not found")
    void findByIdThrowsObjectFoundException() {
        BDDMockito.when(userRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(ObjectNotFoundException.class)
                .isThrownBy(() -> userService.findById(1));
    }

    @Test
    @DisplayName("save and return user id when succesfull")
    void save() {
        User user = UserCreator.createValidUser();
        user.setCars(List.of(CartCreator.createValidCar()));
        Assertions.assertThat(userService.save(user)).isEqualTo(user.getId().toString());
    }

    @Test
    @DisplayName("save throws ObjectFoundException when email exists")
    void saveThrowsEmailExists() {
        BDDMockito.when(userRepositoryMock.existsByEmail(ArgumentMatchers.anyString())).thenReturn(Boolean.TRUE);

        Assertions.assertThatExceptionOfType(ObjectFoundException.class)
                .isThrownBy(() -> userService.save(UserCreator.createUserToBeSaved()));
    }

    @Test
    @DisplayName("save throws ObjectFoundException when login exists")
    void saveThrowsLoginExists() {
        BDDMockito.when(userRepositoryMock.existsByLogin(ArgumentMatchers.anyString())).thenReturn(Boolean.TRUE);

        Assertions.assertThatExceptionOfType(ObjectFoundException.class)
                .isThrownBy(() -> userService.save(UserCreator.createUserToBeSaved()));
    }

    @Test
    void delete() {
        User user = UserCreator.createValidUser();
        Assertions.assertThatCode(
                () -> userService.delete(user.getId())
        ).doesNotThrowAnyException();
    }

    @Test
    void update() {
        User user = UserCreator.createValidUser();
        Assertions.assertThatCode(
                () -> userService.update(user.getId(), user)
        ).doesNotThrowAnyException();
    }

//    @Test
//    void whoami() {
//    }
}