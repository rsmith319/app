package ca.grp1.sabs.app.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "providers")
public class ProviderSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "Business name is required")
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String businessName;

    @NotBlank(message = "Service type is required")
    @Column(nullable = false, length = 100)
    private String serviceType;

    @Column(length = 500)
    private String description;

    @Column(nullable = false, length = 20)
    private String phoneNumber;

    @Column(nullable = false, length = 120)
    private String email;

    @Column(nullable = false)
    private Boolean available;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    // 🔥 Relationship with appointments
    @JsonIgnore
    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
    private List<Appointment> appointments = new ArrayList<>();

    public ProviderSchema() {}

    public ProviderSchema(String businessName, String serviceType, String description,
                          String phoneNumber, String email) {
        this.businessName = businessName;
        this.serviceType = serviceType;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.available = true;
    }

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;

        if (this.available == null) {
            this.available = true;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Getters & Setters

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getBusinessName() { return businessName; }
    public void setBusinessName(String businessName) { this.businessName = businessName; }

    public String getServiceType() { return serviceType; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Boolean getAvailable() { return available; }
    public void setAvailable(Boolean available) { this.available = available; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public List<Appointment> getAppointments() { return appointments; }
    public void setAppointments(List<Appointment> appointments) { this.appointments = appointments; }
}