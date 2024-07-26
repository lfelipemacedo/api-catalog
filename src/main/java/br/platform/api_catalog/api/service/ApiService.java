package br.platform.api_catalog.api.service;

import br.platform.api_catalog.api.model.CreateApiRequest;
import br.platform.api_catalog.api.model.CreateApiResponse;
import br.platform.api_catalog.api.model.UpdateApiRequest;
import br.platform.api_catalog.api.model.UpdateApiResponse;
import br.platform.api_catalog.api.repository.ApiRepository;
import br.platform.api_catalog.api.repository.entity.ApiJpaEntity;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiService {

    @Autowired
    private ApiRepository repository;

    public CreateApiResponse create(CreateApiRequest request) {
        final var name = request.name();
        final var description = request.description();

        if (existsByName(name)) {
            throw new IllegalArgumentException("API already exists");
        }

        ApiJpaEntity createdApi = repository.save(ApiJpaEntity.create(name, description));

        return CreateApiResponse.with(createdApi.getId(), createdApi.getName(), createdApi.getDescription());
    }

    public UpdateApiResponse update(Long id, UpdateApiRequest request) {
        final var name = request.name();
        final var description = request.description();

        if (existsByName(name)) {
            throw new IllegalArgumentException("APIs name already exist");
        }

        ApiJpaEntity apiEntity = findByIdOrElseThrow(id)
                .update(name, description);

        ApiJpaEntity updatedApiEntity = repository.save(apiEntity);

        return UpdateApiResponse.with(updatedApiEntity.getId(), updatedApiEntity.getName(), updatedApiEntity.getDescription());
    }

    private ApiJpaEntity findByIdOrElseThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("API does not exist"));
    }

    private boolean existsByName(String name) {
        return repository.existsByName(name);
    }
}
