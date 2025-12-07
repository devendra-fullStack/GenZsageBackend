package xyz.genzsage.genzsagebackend.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
    private String identity;

    @Column(nullable = false)
    private String profileName;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    private LocalDate birthday;

    private String country;

    @Builder.Default
    private Long languagePreference = 0L;   //here 0 represents english which is also default language

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    private Instant lastLogin;

    private String profilePicUrl;

    @Builder.Default
    private boolean isDeleted = false;

    @Builder.Default
    private boolean isPrivate = false;

    @Column(columnDefinition = "TEXT")
    private String bio;

    // FOLLOWING / FOLLOWERS RELATIONSHIP
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

    // PREFERRED TAGS (user favorites)
    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "sage_preferred_tags",
            joinColumns = @JoinColumn(name = "sage_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> preferredTags = new HashSet<>();

    @PrePersist
    private void prePersist() {
        Instant now = Instant.now();

        if (createdAt == null) createdAt = now;
        if (updatedAt == null) updatedAt = now;
        if (lastLogin == null) lastLogin = now;
    }
}
