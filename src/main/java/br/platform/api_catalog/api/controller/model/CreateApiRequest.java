package br.platform.api_catalog.api.controller.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateApiRequest(
        @NotNull(message = "Name is required")
        @Size(min = 3, max = 50, message = "Name must be between 3 and 0 characters")
        String name,
        String description
) {
}
