package ca.grp1.sabs.app.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.grp1.sabs.app.model.UserSchema;

@Repository
public interface UserRepository extends JpaRepository<UserSchema, String> {
    Optional<UserSchema> findByEmail(String email);
    boolean existsByEmail(String email);
}