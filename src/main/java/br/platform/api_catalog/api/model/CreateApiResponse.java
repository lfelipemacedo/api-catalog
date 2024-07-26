package br.platform.api_catalog.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record CreateApiResponse(
        @JsonProperty("id")
        Long id,
        @JsonProperty("name")
        String name,
        @JsonProperty("description")
        String description) implements Serializable {
    public CreateApiResponse {
    }

    public static CreateApiResponse with(Long id, String name, String description) {
        return new CreateApiResponse(id, name, description);
    }
}
