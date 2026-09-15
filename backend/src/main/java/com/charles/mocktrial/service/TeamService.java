package com.charles.mocktrial.service;

import java.util.UUID;
import java.util.List;

import org.springframework.stereotype.Service;

import com.charles.mocktrial.model.Team;
import com.charles.mocktrial.repository.TeamMembershipRepository;
import com.charles.mocktrial.repository.TeamRepository;
import com.charles.mocktrial.repository.UserRepository;

import jakarta.transaction.Transactional;

import com.charles.mocktrial.model.TeamMembership;
import com.charles.mocktrial.model.TeamRole;
import com.charles.mocktrial.model.User;

@Service 
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamMembershipRepository membershipRepository;
    private final UserRepository userRepository;

    public TeamService(TeamRepository teamRepository, TeamMembershipRepository membershipRepository, UserRepository userRepository) {
        this.teamRepository = teamRepository;
        this.membershipRepository = membershipRepository;
        this.userRepository = userRepository;
    }
    
    @Transactional 
    public Team createTeam(String name, UUID creatorId) {
        User creator = userRepository.findById(creatorId)
            .orElseThrow(() -> new IllegalArgumentException("Usser not found"));
        
        Team team = teamRepository.save(new Team(name));
        TeamMembership membership = new TeamMembership(creator, team, TeamRole.ADMIN);

        membershipRepository.save(membership);

        return team;
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
