package br.platform.api_catalog.api.repository;

import br.platform.api_catalog.api.repository.entity.ApiJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiRepository extends JpaRepository<ApiJpaEntity, Long> {
    boolean existsByName(String name);
}
