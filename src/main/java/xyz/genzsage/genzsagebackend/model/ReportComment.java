package xyz.genzsage.genzsagebackend.model;


import jakarta.persistence.*;

import java.time.Instant;

@Entity
public class ReportComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Sage reporter;

    @ManyToOne
    private Comment comment;

    private String reason;
    private Instant createdAt = Instant.now();
}

