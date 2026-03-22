package ca.grp1.sabs.app.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "Service name is required")
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String serviceName;

    @Size(max = 500)
    @Column(length = 500)
    private String notes;

    @NotNull(message = "Appointment date/time is required")
    @Future(message = "Appointment must be in the future")
    @Column(nullable = false)
    private LocalDateTime appointmentDateTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private AppointmentStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({ "appointmentsAsCustomer", "appointmentsAsProvider" })
    private UserSchema user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "provider_id", nullable = false)
    @JsonIgnoreProperties({ "appointments" })
    private ProviderSchema provider;

    public enum AppointmentStatus {
        PENDING,
        CONFIRMED,
        CANCELLED,
        COMPLETED
    }

    public Appointment() {
    }

    public Appointment(
            String serviceName,
            String notes,
            LocalDateTime appointmentDateTime,
            AppointmentStatus status,
            UserSchema user,
            ProviderSchema provider) {
        this.serviceName = serviceName;
        this.notes = notes;
        this.appointmentDateTime = appointmentDateTime;
        this.status = status;
        this.user = user;
        this.provider = provider;
    }

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;

        if (this.status == null) {
            this.status = AppointmentStatus.PENDING;
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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public UserSchema getUser() {
        return user;
    }

    public void setUser(UserSchema user) {
        this.user = user;
    }

    public ProviderSchema getProvider() {
        return provider;
    }

    public void setProvider(ProviderSchema provider) {
        this.provider = provider;
    }
}