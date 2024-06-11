package br.platform.api_catalog.api.controller.model;

import br.platform.api_catalog.api.entity.ApiEntity;

public record CreateApiResponse(
        String name,
        String description) {
    public static CreateApiResponse with(ApiEntity createdApi) {
        return new CreateApiResponse(createdApi.getName(), createdApi.getDescription());
    }
}
