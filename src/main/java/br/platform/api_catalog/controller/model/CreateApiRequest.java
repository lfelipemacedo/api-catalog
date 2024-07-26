package br.platform.api_catalog.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record CreateApiRequest(
        @NotNull
        @Min(value = 3, message = "Name must have 3 or more characters")
        @JsonProperty("name")
        String name,
        @JsonProperty("description")
        String description) implements Serializable {
    public static CreateApiRequest with(String name, String description) {
        return new CreateApiRequest(name, description);
    }
}
