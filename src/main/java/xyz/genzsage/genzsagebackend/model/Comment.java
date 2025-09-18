package xyz.genzsage.genzsagebackend.model;


import jakarta.persistence.*;

import java.time.Instant;

// Comment.java
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Sage author;

    @ManyToOne
    private Aphorism aphorism;

    @ManyToOne
    private Comment parent; // replies

    private String content;
    private Instant createdAt;

    private int upvotes;
    private int downvotes;
}
