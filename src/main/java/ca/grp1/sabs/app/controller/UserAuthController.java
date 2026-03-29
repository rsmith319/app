package ca.grp1.sabs.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.grp1.sabs.app.data.LoginRequest;
import ca.grp1.sabs.app.model.UserSchema;
import ca.grp1.sabs.app.repo.UserRepository;
import jakarta.validation.constraints.Email;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/users")
public class UserAuthController {

    private final UserRepository userRepository;

    public UserAuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<UserSchema> createUser(@RequestBody UserSchema user) {
        UserSchema savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping
    public List<UserSchema> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserSchema> getUserByEmail(@PathVariable @Email String email) {
        Optional<UserSchema> user = userRepository.findByEmail(email);

        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/login")
    public ResponseEntity<UserSchema> login(@RequestBody LoginRequest request) {
        Optional<UserSchema> user = userRepository.findByEmail(request.getEmail());

        if (user.isPresent() && user.get().getPassword().equals(request.getPassword())) {
            return ResponseEntity.ok(user.get());
        }

        return ResponseEntity.status(401).build();
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable @Email String email) {
        Optional<UserSchema> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        userRepository.delete(user.get());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/health")
    public String hello() {
        return "User API is running 🚀";
    }
}