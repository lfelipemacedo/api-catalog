package br.platform.api_catalog.controller;

import br.platform.api_catalog.controller.model.CreateApiRequest;
import br.platform.api_catalog.controller.model.CreateApiResponse;
import br.platform.api_catalog.repository.ApiRepository;
import br.platform.api_catalog.repository.entity.ApiJpaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/apis")
public class ApiController {

    private final ApiRepository repository;

    @Autowired
    public ApiController(ApiRepository repository) {
        this.repository = repository;
    }

    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<CreateApiResponse> create(@RequestBody CreateApiRequest request, UriComponentsBuilder uriBuilder) {
        ApiJpaEntity createdApi = repository.save(ApiJpaEntity.create(request.name(), request.description()));
        URI uri = uriBuilder.buildAndExpand("/{id}").expand(createdApi.getId()).toUri();
        return ResponseEntity.created(uri)
                .body(CreateApiResponse.with(createdApi.getName(), createdApi.getDescription()));
    }

}
