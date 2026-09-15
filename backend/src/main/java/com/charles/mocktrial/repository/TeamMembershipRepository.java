package com.charles.mocktrial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.charles.mocktrial.model.TeamMembership;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

public interface TeamMembershipRepository extends JpaRepository<TeamMembership, UUID> {

    List<TeamMembership> findByUserId(UUID userId);
    Optional<TeamMembership> findByUserIdAndTeamId(UUID id, UUID teamID);
}