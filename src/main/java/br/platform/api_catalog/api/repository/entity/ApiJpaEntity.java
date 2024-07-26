package br.platform.api_catalog.api.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity(name = "APIS")
@Table(name = "APIS")
public class ApiJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotBlank
    @Size(min = 3, max = 50, message = "Name must have 3 or more characters")
    private String name;
    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;

    public ApiJpaEntity() {
    }

    private ApiJpaEntity(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    private ApiJpaEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static ApiJpaEntity create(String name, String description) {
        return new ApiJpaEntity(name, description);
    }

    public static ApiJpaEntity with(Long expectedId, String expextedName, String expectedDescription) {
        return new ApiJpaEntity(expectedId, expextedName, expectedDescription);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
