package xyz.genzsage.genzsagebackend.dto;


import lombok.Data;

@Data

public class AvailabilityResponse {
    private final boolean available;
    private final String message;
}
