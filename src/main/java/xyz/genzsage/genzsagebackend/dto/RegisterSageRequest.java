package xyz.genzsage.genzsagebackend.dto;


import lombok.*;
import xyz.genzsage.genzsagebackend.model.Tag;
import xyz.genzsage.genzsagebackend.model.enums.Gender;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RegisterSageRequest{

    private String identity;        // user-defined ID
    private String profileName;     // display name
    private String email;           // email for login
    private String phoneNumber;     // optional
    private String password;        // plain password (to be hashed in service)
    private LocalDate birthday;     // optional
    private Double latitude;        // optional
    private Double longitude;       // optional
    private String country;
    private String postalCode;
    private Gender gender;          // enum (MALE, FEMALE, OTHER, etc.)
    private String bio;             // profile description
}