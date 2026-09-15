package com.charles.mocktrial.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.charles.mocktrial.model.MockTrialCase;

import java.util.List;

public interface MockTrialCaseRepository extends JpaRepository<MockTrialCase, UUID>{
    List<MockTrialCase> findByTeamId(UUID teamID);
    
}