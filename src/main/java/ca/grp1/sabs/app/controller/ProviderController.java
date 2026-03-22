package ca.grp1.sabs.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ca.grp1.sabs.app.model.ProviderSchema;
import ca.grp1.sabs.app.repo.ProviderRepository;

@RestController
@RequestMapping("/api/providers")
public class ProviderController {

    private final ProviderRepository providerRepository;

    public ProviderController(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @GetMapping
    public List<ProviderSchema> getAll() {
        return providerRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return providerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody ProviderSchema updated) {
        Optional<ProviderSchema> providerOpt = providerRepository.findById(id);
        if (providerOpt.isEmpty()) return ResponseEntity.notFound().build();

        ProviderSchema provider = providerOpt.get();
        provider.setBusinessName(updated.getBusinessName());
        provider.setServiceType(updated.getServiceType());
        provider.setDescription(updated.getDescription());
        provider.setPhoneNumber(updated.getPhoneNumber());
        provider.setEmail(updated.getEmail());
        provider.setAvailable(updated.getAvailable());

        return ResponseEntity.ok(providerRepository.save(provider));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (!providerRepository.existsById(id)) return ResponseEntity.notFound().build();
        providerRepository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }
}
