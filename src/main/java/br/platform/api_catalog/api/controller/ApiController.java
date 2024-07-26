package br.platform.api_catalog.api.controller;

import br.platform.api_catalog.api.model.CreateApiRequest;
import br.platform.api_catalog.api.model.CreateApiResponse;
import br.platform.api_catalog.api.service.ApiService;
import jakarta.validation.Valid;
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

}
