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

import com.charles.mocktrial.dto.CreateTeamRequest;
import com.charles.mocktrial.model.Team;
import com.charles.mocktrial.service.TeamService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable UUID id) {
        return teamService.getTeamById(id);
    }
    
    @GetMapping("/user/{userId}")
    public List<Team> getTeamsForUser(@PathVariable UUID userId) {
        return teamService.getTeamsForUser(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Team createTeam(@Valid @RequestBody CreateTeamRequest request) {
        return teamService.createTeam(request.getName(), request.getCreatorId());
        }
}
