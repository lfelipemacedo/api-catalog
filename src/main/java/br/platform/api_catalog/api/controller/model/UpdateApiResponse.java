package br.platform.api_catalog.api.controller.model;

import br.platform.api_catalog.api.entity.ApiEntity;

public record UpdateApiResponse(String name, String description) {
    public static UpdateApiResponse with(ApiEntity api) {
        return new UpdateApiResponse(api.getName(), api.getDescription());
    }
}
