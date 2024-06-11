package br.platform.api_catalog.api.repository;

import br.platform.api_catalog.api.entity.ApiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiRepository extends JpaRepository<ApiEntity, Long> {
}
