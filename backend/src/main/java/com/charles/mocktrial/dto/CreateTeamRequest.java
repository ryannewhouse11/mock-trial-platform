package com.charles.mocktrial.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import jakarta.validation.constraints.NotNull;

public class CreateTeamRequest {

    @NotBlank(message = "Team name is Required")
    @Size(max = 255, message = "Team name cannot exceed 255 characters")
    private String name;

    @NotNull(message = "Creator ID is required")
    private UUID creatorId;

    public CreateTeamRequest() {

    }

    public CreateTeamRequest(String name, UUID creatorID) {
        setName(name);
        setCreatorId(creatorID);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getCreatorId() {
        return this.creatorId;
    }

    public void setCreatorId(UUID creatorId) {
        this.creatorId = creatorId;
    }
}