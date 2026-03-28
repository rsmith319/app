package ca.grp1.sabs.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.grp1.sabs.app.model.UserSchema;
import ca.grp1.sabs.app.repo.UserRepository;
import jakarta.validation.constraints.Email;

@RestController
@RequestMapping("/api/v1/users")
public class UserAuthController {

    private final UserRepository userRepository;

    // Constructor Injection 
    public UserAuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // =========================
    // CREATE USER
    // =========================
    @PostMapping
    public ResponseEntity<UserSchema> createUser(@RequestBody UserSchema user) {
        UserSchema savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    // =========================
    // GET ALL USERS
    // =========================
    @GetMapping
    public List<UserSchema> getAllUsers() {
        return userRepository.findAll();
    }

    // =========================
    // GET USER BY EMAIL
    // =========================
    @GetMapping("/api/v1/u1/{email}")
    public ResponseEntity<UserSchema> getUserByEmail(@PathVariable @Email String email) {
        Optional<UserSchema> user = userRepository.findByEmail(email);

        return user.map(ResponseEntity::ok)
        
                .orElseGet(() -> ResponseEntity.status(401).body(null));
    }

    // =========================
    // UPDATE USER
    // =========================
    @PutMapping("/api/v1/users/{email}")
    public ResponseEntity<UserSchema> updateUser(
            @PathVariable @Email String email,
            @RequestBody UserSchema updatedUser
    ) {
        return userRepository.findByEmail(email).map(user -> {

            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setEmail(updatedUser.getEmail());
            user.setPhoneNumber(updatedUser.getPhoneNumber());

           
            if (updatedUser.getPassword() != null) {
                user.setPassword(updatedUser.getPassword());
            }

            UserSchema saved = userRepository.save(user);
            return ResponseEntity.ok(saved);

        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // =========================
    // DELETE USER
    // =========================
    @DeleteMapping("/api/v1/users/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable @Email String email) {

        if (!userRepository.existsById(email)) {
            return ResponseEntity.notFound().build();
        }

        userRepository.deleteById(email);
        return ResponseEntity.ok().build();
    }

    // =========================
    // TEST ROUTE
    // =========================
    @GetMapping("/")
    public String hello() {
        return "User API is running 🚀";
    }

}
