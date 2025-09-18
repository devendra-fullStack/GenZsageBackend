package xyz.genzsage.genzsagebackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import xyz.genzsage.genzsagebackend.model.enums.Gender;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Sage entity represents a registered user in the system.
 * <p>
 * Notes:
 * - identity is user-defined (not auto-generated)
 * - passwordHash should never be exposed in API responses
 * - createdAt is automatically set at creation time
 */
@Entity
@Table(name = "sages")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sage {

    @Id
    @Column(nullable = false, unique = true)
    private String identity;  // user-defined ID for unique reference and also for JWT payload

    @Column(nullable = false)
    private String profileName; // display name for other users

    @Column(nullable = false)
    private String passwordHash; // securely stored hash of the password

    @Column(nullable = false, unique = true)
    private String email; // email for login, recovery, OAuth, etc.



    @Column(unique = true)
    private String phoneNumber; // user’s phone number(as a 2factor auth later)

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;


    private LocalDate birthday;
    @LastModifiedDate
    private Instant updatedAt;

    private Instant lastLogin;

    private String profilePicUrl;

    private String country; // user’s country (audience checking)

    private String postalCode;

    private double latitude;
    private double longitude;
    private Gender gender;
    @Builder.Default
    private boolean isDeleted = false;
    @Builder.Default
    private boolean isPrivate=false;



    @Column(columnDefinition = "TEXT")
    private String bio; // longer text field for profile description

    //Follower and Following relationship
    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "sage_following",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id")
    )
    private Set<Sage> following = new HashSet<>();

    @Builder.Default
    @ManyToMany(mappedBy = "following")
    private Set<Sage> followers = new HashSet<>();


    @PrePersist
    private void prePersist() {
        Instant now = Instant.now();

        // createdAt will already be handled by @CreatedDate,
        // but we can ensure it's initialized in case auditing is not enabled
        if (createdAt == null) {
            createdAt = now;
        }

        // set updatedAt on creation too
        if (updatedAt == null) {
            updatedAt = now;
        }

        // ensure default values
        if (lastLogin == null) {
            lastLogin = now;
        }
    }



}
