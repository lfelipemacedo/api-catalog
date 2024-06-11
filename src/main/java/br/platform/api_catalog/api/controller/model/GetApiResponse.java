package br.platform.api_catalog.api.controller.model;

import br.platform.api_catalog.api.entity.ApiEntity;

public record GetApiResponse(String name, String description, String createdAt, String updatedAt, String deletedAt) {
    public static GetApiResponse with(ApiEntity apiEntity) {
        return new GetApiResponse(apiEntity.getName(), apiEntity.getDescription(), apiEntity.getCreatedAt().toString(),
                apiEntity.getUpdatedAt().toString(), apiEntity.getDeletedAt().toString());
    }
}
