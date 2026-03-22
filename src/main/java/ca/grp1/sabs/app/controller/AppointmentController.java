package ca.grp1.sabs.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ca.grp1.sabs.app.model.Appointment;
import ca.grp1.sabs.app.model.ProviderSchema;
import ca.grp1.sabs.app.model.UserSchema;
import ca.grp1.sabs.app.repo.AppointmentRepository;
import ca.grp1.sabs.app.repo.ProviderRepository;
import ca.grp1.sabs.app.repo.UserRepository;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final ProviderRepository providerRepository;

    public AppointmentController(
            AppointmentRepository appointmentRepository,
            UserRepository userRepository,
            ProviderRepository providerRepository) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
        this.providerRepository = providerRepository;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Appointment appointment) {
        Optional<UserSchema> customer = userRepository.findById(appointment.getCustomer().getId());
        Optional<ProviderSchema> provider = providerRepository.findById(appointment.getProvider().getId());

        if (customer.isEmpty() || provider.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid customer or provider");
        }

        appointment.setCustomer(customer.get());
        appointment.setProvider(provider.get());

        return ResponseEntity.ok(appointmentRepository.save(appointment));
    }

    @GetMapping
    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }
}
