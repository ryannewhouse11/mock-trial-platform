package com.charles.mocktrial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.charles.mocktrial.model.Team;
import java.util.UUID;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, UUID>{
    Optional<Team> findByName(String name);
}
