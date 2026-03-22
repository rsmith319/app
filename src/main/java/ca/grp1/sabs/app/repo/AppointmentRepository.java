package ca.grp1.sabs.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.grp1.sabs.app.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {
    List<Appointment> findByUserId(String userId);
    List<Appointment> findByProviderId(String providerId);
}
