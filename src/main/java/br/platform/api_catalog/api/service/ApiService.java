package br.platform.api_catalog.api.service;

import br.platform.api_catalog.api.model.CreateApiRequest;
import br.platform.api_catalog.api.model.CreateApiResponse;
import br.platform.api_catalog.api.repository.ApiRepository;
import br.platform.api_catalog.api.repository.entity.ApiJpaEntity;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ApiService {

    @Autowired
    private Validator validator;

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

    private boolean existsByName(String name) {
        return repository.existsByName(name);
    }
}
