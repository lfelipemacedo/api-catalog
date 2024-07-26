package br.platform.api_catalog.api.controller;

import br.platform.api_catalog.api.model.CreateApiRequest;
import br.platform.api_catalog.api.model.CreateApiResponse;
import br.platform.api_catalog.api.model.UpdateApiRequest;
import br.platform.api_catalog.api.model.UpdateApiResponse;
import br.platform.api_catalog.api.repository.entity.ApiJpaEntity;
import br.platform.api_catalog.api.service.ApiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/apis")
public class ApiController {

    private final ApiService service;

    @Autowired
    public ApiController(ApiService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CreateApiResponse> create(@Valid @RequestBody CreateApiRequest request, UriComponentsBuilder uriBuilder) {
        CreateApiResponse response = service.create(request);
        URI uri = uriBuilder.buildAndExpand("/{id}").expand(response.id()).toUri();
        return ResponseEntity
                .created(uri)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateApiResponse> update(@Valid @PathVariable("id") Long id, @RequestBody UpdateApiRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

}
