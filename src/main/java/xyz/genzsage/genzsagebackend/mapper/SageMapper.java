package xyz.genzsage.genzsagebackend.mapper;

import xyz.genzsage.genzsagebackend.dto.RegisterSageRequest;
import xyz.genzsage.genzsagebackend.model.Sage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class SageMapper {

    private final PasswordEncoder passwordEncoder;

    public SageMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Sage toSage(RegisterSageRequest request) {
        return Sage.builder()
                .identity(request.getIdentity())
                .profileName(request.getProfileName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .birthday(request.getBirthday())
                .country(request.getCountry())
                .bio(request.getBio())
                .isPrivate(request.isPrivate())
                .profilePicUrl(request.getProfilePicUrl())
                .lastLogin(Instant.now())
                // languagePreference is optional, default 0L in entity
                .build();
    }
}
