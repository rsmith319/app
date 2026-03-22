package ca.grp1.sabs.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ca.grp1.sabs.app.model.UserSchema;
import ca.grp1.sabs.app.repo.UserRepository;

@RestController
@RequestMapping("/api/users")
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
    // GET USER BY ID
    // =========================
    @GetMapping("/{id}")
    public ResponseEntity<UserSchema> getUserById(@PathVariable String id) {
        Optional<UserSchema> user = userRepository.findById(id);

        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // =========================
    // UPDATE USER
    // =========================
    @PutMapping("/{id}")
    public ResponseEntity<UserSchema> updateUser(
            @PathVariable String id,
            @RequestBody UserSchema updatedUser
    ) {
        return userRepository.findById(id).map(user -> {

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
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {

        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        userRepository.deleteById(id);
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
