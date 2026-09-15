package com.charles.mocktrial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.charles.mocktrial.model.Team;
import java.util.UUID;

public interface TeamRepository extends JpaRepository<Team, UUID>{
    
}
