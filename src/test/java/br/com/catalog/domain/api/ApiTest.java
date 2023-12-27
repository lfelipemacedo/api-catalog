package br.com.catalog.domain.api;


import br.com.catalog.domain.api.Api;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ApiTest {

    @Test
    public void createApiTest() {
        final var expectedName = "Api Test";
        final var expectedDescription = "My first Api";

        Api api = Api.with(expectedName, expectedDescription);

        Assertions.assertEquals(expectedName, api.getName());
        Assertions.assertEquals(expectedDescription, api.getDescription());
    }

    @Test
    public void createApiWithIdTest() {
        final var expectedId = UUID.randomUUID().toString();
        final var expectedName = "Api Test";
        final var expectedDescription = "My first Api";

        Api api = Api.with(expectedId, expectedName, expectedDescription);

        Assertions.assertEquals(expectedId, api.getId());
        Assertions.assertEquals(expectedName, api.getName());
        Assertions.assertEquals(expectedDescription, api.getDescription());
    }

    @Test
    public void createApiWithEmptyDescriptionTest() {
        final var expectedName = "Api Test";
        final var expectedDescription = "";

        Api api = Api.with(expectedName, expectedDescription);

        Assertions.assertEquals(expectedName, api.getName());
        Assertions.assertEquals("", api.getDescription());
    }

    @Test
    public void changeApiNameTest() {
        final var expectedName = "New Name";
        final var expectedDescription = "Old Description";

        Api api = Api.with("Old Name", expectedDescription);

        api.changeName(expectedName);

        Assertions.assertEquals(expectedName, api.getName());
        Assertions.assertEquals(expectedDescription, api.getDescription());
    }

    @Test
    public void changeApiDescriptionTest() {
        final var expectedName = "Old Name";
        final var expectedDescription = "New Description";

        Api api = Api.with(expectedName, "Old Description");

        api.changeDescription(expectedDescription);

        Assertions.assertEquals(expectedName, api.getName());
        Assertions.assertEquals(expectedDescription, api.getDescription());
    }

    @Test
    public void changeApiDescriptionWithEmptyTest() {
        final var expectedName = "Old Name";
        final var expectedDescription = "";

        Api api = Api.with(expectedName, "Old Description");

        api.changeDescription(expectedDescription);

        Assertions.assertEquals(expectedName, api.getName());
        Assertions.assertEquals(expectedDescription, api.getDescription());
    }

    @Test
    public void createApiWithEmptyNameTest() {
        final var expectedExceptionMessage = "Name is required";

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Api.with("", "My first API");
        });

        Assertions.assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    public void createApiWithNullNameTest() {
        final var expectedExceptionMessage = "Name is required";

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Api.with(null, "My first API");
        });

        Assertions.assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    public void createApiWithLessThanThreeCharsNameTest() {
        final var expectedExceptionMessage = "Api`s name has to be greater than 3 characters";

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Api.with("Ap", "My first API");
        });

        Assertions.assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    public void changeApiWithLessThanThreeCharsNameTest() {
        final var expectedExceptionMessage = "Api`s name has to be greater than 3 characters";
        Api api = Api.with("Api Test", "My first API");

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            api.changeName("Ap");
        });

        Assertions.assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    public void changeApiWithNullNameTest() {
        final var expectedExceptionMessage = "Name is required";
        Api api = Api.with("Api Test", "My first API");

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            api.changeName(null);
        });

        Assertions.assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    public void changeApiWithEmptyNameTest() {
        final var expectedExceptionMessage = "Name is required";
        Api api = Api.with("Api Test", "My first API");

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            api.changeName("");
        });

        Assertions.assertEquals(expectedExceptionMessage, exception.getMessage());
    }
}
