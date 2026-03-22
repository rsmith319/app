package ca.grp1.sabs.app.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class UserSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "First name is required")
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String lastName;

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is required")
    @Column(nullable = false, unique = true, length = 120)
    private String email;

    @NotBlank(message = "Password is required")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "Phone number is required")
    @Column(nullable = false, length = 20)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Role role;

    @Column(nullable = false)
    private Boolean enabled;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Appointment> appointmentsAsCustomer = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Appointment> appointmentsAsProvider = new ArrayList<>();

    public enum Role {
        CUSTOMER,
        SERVICE_PROVIDER,
        ADMIN
    }

    public UserSchema() {
    }

    public UserSchema(String firstName, String lastName, String email, String password, String phoneNumber, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        if (this.enabled == null) {
            this.enabled = true;
        }

        if (this.role == null) {
            this.role = Role.CUSTOMER;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<Appointment> getAppointmentsAsCustomer() {
        return appointmentsAsCustomer;
    }

    public void setAppointmentsAsCustomer(List<Appointment> appointmentsAsCustomer) {
        this.appointmentsAsCustomer = appointmentsAsCustomer;
    }

    public List<Appointment> getAppointmentsAsProvider() {
        return appointmentsAsProvider;
    }

    public void setAppointmentsAsProvider(List<Appointment> appointmentsAsProvider) {
        this.appointmentsAsProvider = appointmentsAsProvider;
    }
}