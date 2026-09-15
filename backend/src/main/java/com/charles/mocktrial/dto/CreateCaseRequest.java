package com.charles.mocktrial.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateCaseRequest {
    @NotNull(message = "Team ID is required")
    private UUID teamId;

    @NotBlank(message = "Case name is required")
    @Size(max = 255, message = "Case name cannot exceed 255 characters")
    private String name;

    @Size(max = 2000, message = "Description cannot exceed 2000 characters")
    private String description;

    public CreateCaseRequest() {
        
    }

    public CreateCaseRequest(UUID teamId, String name, String description) {
        setTeamId(teamId);
        setTeamName(name);
        setDescription(description);
    }

    public UUID getTeamId() {
        return teamId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setTeamId(UUID id) {
        this.teamId = id;
    }

    public void setTeamName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
