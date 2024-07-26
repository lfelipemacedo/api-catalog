package br.platform.api_catalog.api.model;

public record UpdateApiResponse(Long id, String name, String description) {
    public static UpdateApiResponse with(Long id, String name, String description) {
        return new UpdateApiResponse(id, name, description);
    }
}
