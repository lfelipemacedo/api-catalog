package br.platform.api_catalog.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record CreateApiRequest(
        @NotBlank
        @Size(min = 3, message = "Name must have 3 or more characters")
        @JsonProperty("name")
        String name,
        @Size(max = 255, message = "Description must be less than 255 characters")
        @JsonProperty("description")
        String description) implements Serializable {
    public static CreateApiRequest with(String name, String description) {
        return new CreateApiRequest(name, description);
    }
}
