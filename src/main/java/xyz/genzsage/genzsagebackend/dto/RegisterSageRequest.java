package xyz.genzsage.genzsagebackend.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RegisterSageRequest {

    private String identity;        // user-defined ID
    private String profileName;     // display name
    private String email;           // email for login
    private String phoneNumber;     // optional
    private String password;        // plain password (to be hashed in service)
    private LocalDate birthday;     // optional
    private String country;         // user country
    private String bio;             // profile description
    private boolean isPrivate;
    private String profilePicUrl;
}
