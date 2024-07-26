package br.platform.api_catalog.repository.entity;

import jakarta.persistence.*;

@Entity(name = "APIS")
@Table(name = "APIS")
public class ApiJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String description;

    public ApiJpaEntity() {
    }

    private ApiJpaEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static ApiJpaEntity create(String name, String description) {
        return new ApiJpaEntity(name, description);
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
