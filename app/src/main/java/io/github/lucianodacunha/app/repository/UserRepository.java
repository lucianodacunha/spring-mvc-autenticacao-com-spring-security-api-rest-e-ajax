package io.github.lucianodacunha.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import io.github.lucianodacunha.app.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
