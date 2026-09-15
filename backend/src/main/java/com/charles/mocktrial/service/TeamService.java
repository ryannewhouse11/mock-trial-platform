package com.charles.mocktrial.service;

import java.util.UUID;
import java.util.List;

import org.springframework.stereotype.Service;

import com.charles.mocktrial.model.Team;
import com.charles.mocktrial.repository.TeamMembershipRepository;
import com.charles.mocktrial.repository.TeamRepository;
import com.charles.mocktrial.model.TeamMembership;
import com.charles.mocktrial.model.TeamRole;

@Service 
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamMembershipRepository membershipRepository;

    public TeamService(TeamRepository teamRepository, TeamMembershipRepository membershipRepository) {
        this.teamRepository = teamRepository;
        this.membershipRepository = membershipRepository;
    }
    
    public Team createTeam(String name) {
        Team team = new Team(name);
        return teamRepository.save(team);
    }

    public Team getTeamById(UUID id) {
        return teamRepository.findById(id).orElse(null);
    }

    @SuppressWarnings("null")
    public List<Team> getTeamsForUser(UUID userId) {
        List<TeamMembership> memberships = membershipRepository.findByUserId(userId);

        return memberships.stream()
            .map(TeamMembership::getTeam)
            .toList();
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }
}
