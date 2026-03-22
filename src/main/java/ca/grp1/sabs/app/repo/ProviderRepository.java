package ca.grp1.sabs.app.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.grp1.sabs.app.model.ProviderSchema;

public interface ProviderRepository extends JpaRepository<ProviderSchema, String> {
    Optional<ProviderSchema> findByEmail(String email);
    boolean existsByEmail(String email);
}
