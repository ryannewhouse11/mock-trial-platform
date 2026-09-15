package com.charles.mocktrial.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.charles.mocktrial.model.MockTrialCase;
import com.charles.mocktrial.model.Team;
import com.charles.mocktrial.service.CaseService;
import com.charles.mocktrial.dto.CreateCaseRequest;
import com.charles.mocktrial.service.TeamService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cases")
public class CaseController {
    private final CaseService caseService;
    private final TeamService teamService;

    public CaseController(CaseService caseService, TeamService teamService) {
        this.caseService = caseService;
        this.teamService = teamService;
    }

    @GetMapping
    public List<MockTrialCase> getAllCases() {
        return caseService.getAllCases();
    }

    @GetMapping("/{id}")
    public MockTrialCase getCaseById(@PathVariable UUID id) {
        return caseService.getCaseById(id);
    }

    @GetMapping("/team/{teamId}")
    public List<MockTrialCase> getCasesByTeam(@PathVariable UUID teamId) {
        return caseService.getCasesByTeam(teamId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MockTrialCase createCase(@Valid @RequestBody CreateCaseRequest request) {
        Team team = teamService.getTeamById(request.getTeamId());

        if (team == null) {
            throw new IllegalArgumentException("Team Not Found");
        }

        return caseService.createCase(team, request.getName(), request.getDescription());
    }
}