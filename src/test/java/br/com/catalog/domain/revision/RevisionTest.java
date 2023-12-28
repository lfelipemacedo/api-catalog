package br.com.catalog.domain.revision;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class RevisionTest {

    @Test
    public void createRevisionTest() {
        final var expectedBasePath = "/path";
        final var expectedDestination = "https://catalog.free.beeceptor.com";

        Revision revision = Revision.with(expectedBasePath, expectedDestination);

        Assertions.assertNotNull(revision.getId());
        Assertions.assertEquals(expectedBasePath, revision.getBasePath());
        Assertions.assertEquals(expectedDestination, revision.getDestination());
    }

    @Test
    public void createRevisionWithIdTest() {
        final var expectedId = UUID.randomUUID().toString();
        final var expectedBasePath = "/path";
        final var expectedDestination = "https://catalog.free.beeceptor.com";

        Revision revision = Revision.with(expectedId, expectedBasePath, expectedDestination);

        Assertions.assertEquals(expectedId, revision.getId());
        Assertions.assertEquals(expectedBasePath, revision.getBasePath());
        Assertions.assertEquals(expectedDestination, revision.getDestination());
    }

    @Test
    public void createRevisionWithoutDestinationTest() {
        final var expectedBasePath = "/path";
        final var expectedDestination = "";

        Revision revision = Revision.with(expectedBasePath, expectedDestination);

        Assertions.assertNotNull(revision.getId());
        Assertions.assertEquals(expectedBasePath, revision.getBasePath());
        Assertions.assertEquals(expectedDestination, revision.getDestination());
    }

    @Test
    public void changeRevisionPathTest() {
        final var expectedBasePath = "/new-path";
        final var expectedDestination = "";

        Revision revision = Revision.with("/old-path", expectedDestination);
        revision.changeBasePath(expectedBasePath);

        Assertions.assertNotNull(revision.getId());
        Assertions.assertEquals(expectedBasePath, revision.getBasePath());
        Assertions.assertEquals(expectedDestination, revision.getDestination());
    }

    @Test
    public void changeRevisionDestinationTest() {
        final var expectedBasePath = "/new-path";
        final var expectedDestination = "";

        Revision revision = Revision.with("/old-path", expectedDestination);
        revision.changeBasePath(expectedBasePath);

        Assertions.assertNotNull(revision.getId());
        Assertions.assertEquals(expectedBasePath, revision.getBasePath());
        Assertions.assertEquals(expectedDestination, revision.getDestination());
    }

    @Test
    public void createRevisionWithoutBasePathTest() {
        final var expectedExceptionMessage = "Revision`s path is required";

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Revision.with("", "https://catalog.free.beeceptor.com");
        });


        Assertions.assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    public void createRevisionWithBasePathWithoutBarTest() {
        final var expectedExceptionMessage = "Revision`s path must start with a '/'";

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Revision.with("path", "https://catalog.free.beeceptor.com");
        });


        Assertions.assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    public void createRevisionWithBasePathWithSpecialCharsTest() {
        final var expectedExceptionMessage = "Revision`s path must not contain special chars";

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Revision.with("/path!@#", "https://catalog.free.beeceptor.com");
        });


        Assertions.assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    public void createRevisionWithValidDestinationTest() {
        final var expectedExceptionMessage = "Revision`s destination must be valid";

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Revision.with("/path", "catalog.free.beeceptor.com");
        });


        Assertions.assertEquals(expectedExceptionMessage, exception.getMessage());
    }


}
