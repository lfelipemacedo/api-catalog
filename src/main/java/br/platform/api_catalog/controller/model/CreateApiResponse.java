package br.platform.api_catalog.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record CreateApiResponse(
        @JsonProperty("name")
        String name,
        @JsonProperty("description")
        String description) implements Serializable {
    public CreateApiResponse {
    }

    public static CreateApiResponse with(String name, String description) {
        return new CreateApiResponse(name, description);
    }
}
