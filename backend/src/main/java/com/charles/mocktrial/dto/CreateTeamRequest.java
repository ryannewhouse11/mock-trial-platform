package com.charles.mocktrial.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateTeamRequest {

    @NotBlank(message = "Team name is Required")
    @Size(max = 255, message = "Team name cannot exceed 255 characters")
    private String name;

    public CreateTeamRequest() {

    }

    public CreateTeamRequest(String name) {
        setName(name);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}