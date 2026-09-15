package com.charles.mocktrial.service;

import com.charles.mocktrial.repository.MockTrialCaseRepository;
import com.charles.mocktrial.model.Team;
import com.charles.mocktrial.model.MockTrialCase;
import java.util.UUID;

import org.springframework.stereotype.Service;

import java.util.List;

@Service 
public class CaseService {
    private final MockTrialCaseRepository caseRepository;

    public CaseService(MockTrialCaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    public MockTrialCase createCase(Team team, String name,String description) {
        MockTrialCase mockTrialCase = new MockTrialCase(team, name, description);
        return caseRepository.save(mockTrialCase);
    }

    public MockTrialCase getCaseById(UUID id) {
        return caseRepository.findById(id).orElse(null);
    }

    public List<MockTrialCase> getCasesByTeam(UUID teamId) {
        return caseRepository.findByTeamId(teamId);
    }

    public List<MockTrialCase> getAllCases() {
        return caseRepository.findAll();
    }
}