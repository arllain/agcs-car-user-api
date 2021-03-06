package com.arllain.agcscaruserapi.repository;

import com.arllain.agcscaruserapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author arllain
 */
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByLogin(String login);

    User findByLogin(String login);
}