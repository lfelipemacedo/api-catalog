package br.com.catalog.domain.api;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class Api {
    private String id;
    private String name;
    private String description;

    public Api() {
    }

    private Api(String id, String name, String description) {
        validateName(name);
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static Api with(String name, String description) {
        return new Api(UUID.randomUUID().toString(), name, description);
    }

    public static Api with(String id, String name, String description) {
        return new Api(id, name, description);
    }

    private static void validateName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Name is required");
        }

        if (name.length() < 3) {
            throw new IllegalArgumentException("Api`s name has to be greater than 3 characters");
        }
    }

    public void changeName(String name) {
        validateName(name);
        this.name = name;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
