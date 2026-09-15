package com.charles.mocktrial.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity 
@Table(name ="team_memberships",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "team_id"})
    }
)
public class TeamMembership {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne 
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;

    @ManyToOne 
    @JoinColumn(name = "team_id" , nullable = false)
    private Team team;

    @Enumerated(EnumType.STRING)
    private TeamRole role;

    private LocalDateTime joinedAt;

    public TeamMembership(){
    }
    
    public TeamMembership(User user, Team team, TeamRole role) {
        this.user = user;
        this.team = team;
        this.role = role;
        this.joinedAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setID(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public TeamRole getRole() {
        return role;
    }

    public void setRole(TeamRole role) {
        this.role = role;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }
}
