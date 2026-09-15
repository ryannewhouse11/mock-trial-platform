package com.charles.mocktrial.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.charles.mocktrial.model.MockTrialCase;

public interface MockTrialCaseRepository extends JpaRepository<MockTrialCase, UUID>{

    
}