package br.com.catalog.domain.revision;

import br.com.catalog.domain.util.Path;

import java.util.UUID;

public class Revision {

    private String id;
    private String basePath;
    private String destination;

    public Revision() {
    }

    private Revision(String id, String basePath, String destination) {
        this.id = id;
        this.basePath = Path.validateBasePath(basePath);
        this.destination = Path.validateDestination(destination);
    }

    public static Revision with(String basePath, String destination) {
        return new Revision(UUID.randomUUID().toString(), basePath, destination);
    }

    public static Revision with(String id, String basePath, String destination) {
        return new Revision(id, basePath, destination);
    }

    public void changeBasePath(String basePath) {
        this.basePath = Path.validateBasePath(basePath);
    }

    public void changeDestination(String destination) {
        this.destination = Path.validateDestination(destination);
    }

    public String getId() {
        return id;
    }

    public String getBasePath() {
        return basePath;
    }

    public String getDestination() {
        return destination;
    }
}
