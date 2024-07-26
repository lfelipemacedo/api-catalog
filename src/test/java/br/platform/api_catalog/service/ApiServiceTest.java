package br.platform.api_catalog.service;

import br.platform.api_catalog.api.model.CreateApiRequest;
import br.platform.api_catalog.api.model.CreateApiResponse;
import br.platform.api_catalog.api.repository.ApiRepository;
import br.platform.api_catalog.api.repository.entity.ApiJpaEntity;
import br.platform.api_catalog.api.service.ApiService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
public class ApiServiceTest {

    @Autowired
    @InjectMocks
    private ApiService service;

    @Mock
    private ApiRepository repository;

    @Test
    public void shouldCreateApiWithSuccess() throws Exception {
        final var expectedId = 123L;
        final var expextedName = "Test API";
        final var expectedDescription = "My API Description";

        Mockito.when(repository.existsByName(Mockito.anyString())).thenReturn(Boolean.FALSE);
        Mockito.when(repository.save(Mockito.any())).thenReturn(ApiJpaEntity.with(expectedId, expextedName, expectedDescription));

        CreateApiResponse response = service.create(CreateApiRequest.with(expextedName, expectedDescription));

        Assertions.assertEquals(expectedId, response.id());
        Assertions.assertEquals(expextedName, response.name());
        Assertions.assertEquals(expectedDescription, response.description());
    }

    @Test
    public void shouldNotCreateApiWithExistingName() throws Exception {
        final var expextedExceptionMessage = "API already exists";

        Mockito.when(repository.existsByName(Mockito.anyString())).thenReturn(Boolean.TRUE);

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.create(CreateApiRequest.with("Test API", "Description"));
        });

        Assertions.assertEquals(expextedExceptionMessage, exception.getMessage());
    }
}
