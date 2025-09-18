package xyz.genzsage.genzsagebackend.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Question {
    @Id
    public Long id;
}
