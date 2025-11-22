package xyz.genzsage.genzsagebackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
@Setter
public class CheckAvailabilityRequest {
    private String identity;
    private String email;
    private String phoneNumber;
}
