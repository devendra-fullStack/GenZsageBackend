package xyz.genzsage.genzsagebackend.model;

import jakarta.persistence.*;

import java.time.Instant;

// VoteAphorism.java
@Entity
public class VoteAphorism {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Sage voter;

    @ManyToOne
    private Aphorism aphorism;

    // true = upvote, false = downvote
    private boolean upvote;

    private Instant createdAt = Instant.now();
}
