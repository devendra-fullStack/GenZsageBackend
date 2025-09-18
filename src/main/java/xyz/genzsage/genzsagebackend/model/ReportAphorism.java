package xyz.genzsage.genzsagebackend.model;

import jakarta.persistence.*;

import java.time.Instant;

// ReportAphorism.java
@Entity
public class ReportAphorism {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Sage reporter;

    @ManyToOne
    private Aphorism aphorism;

    private String reason;
    private Instant createdAt = Instant.now();
}
