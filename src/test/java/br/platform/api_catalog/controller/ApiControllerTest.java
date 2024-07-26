package br.platform.api_catalog.controller;

import br.platform.api_catalog.MySQLTestContainer;
import br.platform.api_catalog.api.controller.ApiController;
import br.platform.api_catalog.api.model.CreateApiRequest;
import br.platform.api_catalog.api.model.CreateApiResponse;
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
public class ApiControllerTest extends MySQLTestContainer {

    @Autowired
    private ApiController controller;

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldCreateApiWithSuccess() throws Exception {
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

    @Test
    public void shouldNotCreateApiWithNameLessThan3Chars() throws Exception {
        final var expextedName = "Te";
        final var expectedDescription = "My API Description";

        mockMvc.perform(MockMvcRequestBuilders.post("/apis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(CreateApiRequest.with(expextedName, expectedDescription))))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void shouldNotCreateApiWithDescriptionGreaterThan255Chars() throws Exception {
        final var expextedName = "Te";
        final var expectedDescription = """
                At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum 
                deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non 
                provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. 
                Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est 
                eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas 
                assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum 
                necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. 
                Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias 
                consequatur aut perferendis doloribus asperiores repellat.
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/apis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(CreateApiRequest.with(expextedName, expectedDescription))))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void shouldNotCreateApiWithEmptyName() throws Exception {
        final var expextedName = "";
        final var expectedDescription = "My API Description";

        mockMvc.perform(MockMvcRequestBuilders.post("/apis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(CreateApiRequest.with(expextedName, expectedDescription))))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
