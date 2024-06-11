package br.platform.api_catalog.api.controller;

import br.platform.api_catalog.api.controller.model.CreateApiRequest;
import br.platform.api_catalog.api.controller.model.CreateApiResponse;
import br.platform.api_catalog.api.controller.model.GetApiResponse;
import br.platform.api_catalog.api.controller.model.UpdateApiRequest;
import br.platform.api_catalog.api.controller.model.UpdateApiResponse;
import br.platform.api_catalog.api.entity.ApiEntity;
import br.platform.api_catalog.api.repository.ApiRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping
    public ResponseEntity<CreateApiResponse> create(@Valid @RequestBody CreateApiRequest request, UriComponentsBuilder uriBuilder) {
        ApiEntity createdApi = repository.save(ApiEntity.create(request.name(), request.description()));

        URI uri = uriBuilder.path("/apis/{id}").buildAndExpand(createdApi.getId()).toUri();
        return ResponseEntity
                .created(uri)
                .body(CreateApiResponse.with(createdApi));
    }

    @GetMapping
    public ResponseEntity<Page<GetApiResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(repository.findAll(pageable).map(GetApiResponse::with));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetApiResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(GetApiResponse.with(findByIdOrElseThrow(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateApiResponse> update(@Valid @PathVariable("id") Long id, @RequestBody UpdateApiRequest request) {
        ApiEntity api = findByIdOrElseThrow(id)
                .update(request.name(), request.description());
        ApiEntity updatedApi = repository.save(api);

        return ResponseEntity.ok(UpdateApiResponse.with(updatedApi));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        repository.save(findByIdOrElseThrow(id).delete());
        return ResponseEntity.noContent().build();
    }

    private ApiEntity findByIdOrElseThrow(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("API does not exist"));
    }
}
