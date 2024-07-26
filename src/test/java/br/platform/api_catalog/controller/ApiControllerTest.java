package br.platform.api_catalog.controller;

import br.platform.api_catalog.controller.model.CreateApiRequest;
import br.platform.api_catalog.controller.model.CreateApiResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class ApiControllerTest {

    @Autowired
    private ApiController controller;

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void createApiWithSuccess() throws Exception {
        final var expextedName = "Test API";
        final var expectedDescription = "My API Description";

        String stringResponse = mockMvc.perform(MockMvcRequestBuilders.post("/apis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(CreateApiRequest.with(expextedName, expectedDescription))))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        CreateApiResponse createApiResponse = mapper.readValue(stringResponse, CreateApiResponse.class);

        Assertions.assertEquals(expextedName, createApiResponse.name());
        Assertions.assertEquals(expectedDescription, createApiResponse.description());
    }
}
