package xyz.genzsage.genzsagebackend.model;

import jakarta.persistence.*;
import org.aspectj.weaver.patterns.TypePatternQuestions;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

// Aphorism.java (Post)
@Entity
public class Aphorism {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Sage author;

    @OneToOne(cascade = CascadeType.ALL)
    private Question question;

    private Instant createdAt;

    private int upvotes;
    private int downvotes;
    private int reports;

    @ManyToMany
    private Set<Tag> tags = new HashSet<>();
}

