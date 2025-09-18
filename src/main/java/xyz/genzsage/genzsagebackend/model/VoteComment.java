package xyz.genzsage.genzsagebackend.model;

import jakarta.persistence.*;

import java.time.Instant;

// VoteComment.java
@Entity
public class VoteComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Sage voter;

    @ManyToOne
    private Comment comment;

    private boolean upvote;

    private Instant createdAt = Instant.now();
}
