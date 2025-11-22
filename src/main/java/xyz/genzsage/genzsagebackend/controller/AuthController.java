package xyz.genzsage.genzsagebackend.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.genzsage.genzsagebackend.dto.*;
import xyz.genzsage.genzsagebackend.service.AuthService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/register")
    public GlobalResponseDto<AuthResponse> register(@RequestBody RegisterSageRequest request) {
        logger.debug(request.toString());

        AuthResponse authResponse = authService.register(request);
        return GlobalResponseDto.<AuthResponse>builder()
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .message("Registration Successful !!!!!!!")
                .data(authResponse)
                .errors(null)
                .build();
    }

    @PostMapping("/login")
    public GlobalResponseDto<AuthResponse> login(@RequestBody LoginRequest request) {
        logger.debug(request.toString());

        AuthResponse authResponse = authService.login(request);
        return GlobalResponseDto.<AuthResponse>builder()
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .message("Registration Successful !!!!!!!")
                .data(authResponse)
                .errors(null)
                .build();
    }

    @PostMapping("/refresh")
    public GlobalResponseDto<AuthResponse> refresh(@RequestBody RefreshTokenRequest request) {
        logger.debug(request.toString());


        AuthResponse authResponse = authService.refreshToken(request.getRefreshToken());
        return GlobalResponseDto.<AuthResponse>builder()
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .message("Token refreshed successfully")
                .data(authResponse)
                .errors(null)
                .build();
    }

    @PostMapping("/check-availability")
    public GlobalResponseDto<Boolean> checkAvailability(@RequestBody CheckAvailabilityRequest request) {
        logger.debug(request.toString());

        AvailabilityResponse available = authService.isAvailable(request);
        return GlobalResponseDto.<Boolean>builder()
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .message(available.getMessage())
                .data(available.isAvailable())
                .errors(null)
                .build();
    }


}

