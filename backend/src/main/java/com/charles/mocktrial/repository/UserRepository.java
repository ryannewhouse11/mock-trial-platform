package com.charles.mocktrial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.charles.mocktrial.model.User;
import java.util.UUID;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, UUID>{

    Optional<User> findByEmail(String email);
}
