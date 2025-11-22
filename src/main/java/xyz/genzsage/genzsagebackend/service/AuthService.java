package xyz.genzsage.genzsagebackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.genzsage.genzsagebackend.dto.*;
import xyz.genzsage.genzsagebackend.exception.AppException;
import xyz.genzsage.genzsagebackend.mapper.SageMapper;
import xyz.genzsage.genzsagebackend.model.Sage;
import xyz.genzsage.genzsagebackend.repository.SageRepository;
import xyz.genzsage.genzsagebackend.security.JwtService;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final SageRepository sageRepository;
    private final SageMapper sageMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthResponse register(RegisterSageRequest request) {
        Optional<Sage> existing = sageRepository.findByIdentityOrEmailOrPhoneNumber(
                request.getIdentity(), request.getEmail(), request.getPhoneNumber());

        if (existing.isPresent()) {
            Sage conflict = existing.get();
            if (conflict.getIdentity().equals(request.getIdentity())) {
                throw new AppException(HttpStatus.CONFLICT,"Identity already in use "+request.getIdentity());
            }
            if (conflict.getEmail().equals(request.getEmail())) {
                throw new AppException(HttpStatus.CONFLICT,"Email already in use "+request.getEmail());
            }
            if (conflict.getPhoneNumber().equals(request.getPhoneNumber())) {
                throw new AppException(HttpStatus.CONFLICT,"Phone Number already in use "+request.getPhoneNumber());
            }
        }


        Sage sage = sageMapper.toSage(request); // <-- clean conversion

        Sage saved = sageRepository.save(sage);

        String accessToken = jwtService.generateToken(saved.getIdentity(), "login");
        String refreshToken = jwtService.generateToken(saved.getIdentity(), "refresh");

        return new AuthResponse(accessToken, refreshToken);
    }

    public AuthResponse login(LoginRequest request) {
        // authenticate with Spring Security
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getIdentifier(),
                        request.getPassword()
                )
        );

        Sage sage = sageRepository.findByIdentityOrEmailOrPhoneNumber(request.getIdentifier(),request.getIdentifier(),request.getIdentifier())
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND,"User not found"));

        sage.setLastLogin(Instant.now());
        sageRepository.save(sage);

        String accessToken = jwtService.generateToken(sage.getIdentity(), "login");
        String refreshToken = jwtService.generateToken(sage.getIdentity(), "refresh");

        return new AuthResponse(accessToken, refreshToken);
    }

    public AuthResponse refreshToken(String refreshToken) {
        if (!jwtService.isJwtValid(refreshToken)) {
            throw new AppException(HttpStatus.UNAUTHORIZED,"Invalid refresh token");
        }

        String identity = jwtService.extractUserIdentity(refreshToken);
        Sage sage = sageRepository.findById(identity)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND,"User not found"));

        String newAccessToken = jwtService.generateToken(sage.getIdentity(), "login");

        return new AuthResponse(newAccessToken, refreshToken);
    }

    public AvailabilityResponse isAvailable(CheckAvailabilityRequest request) {
        Optional<Sage> existing = sageRepository.findByIdentityOrEmailOrPhoneNumber(
                request.getIdentity(),
                request.getEmail(),
                request.getPhoneNumber()
        );

        if (existing.isPresent()) {
            Sage user = existing.get();

            if (user.getIdentity().equals(request.getIdentity())) {
                return new AvailabilityResponse(false, "Identity already registered");
            }
            if (user.getEmail().equals(request.getEmail())) {
                return new AvailabilityResponse(false, "Email already registered");
            }
            if (user.getPhoneNumber().equals(request.getPhoneNumber())) {
                return new AvailabilityResponse(false, "Phone number already registered");
            }
        }

        return new AvailabilityResponse(true, "Go ahead");
    }

}
