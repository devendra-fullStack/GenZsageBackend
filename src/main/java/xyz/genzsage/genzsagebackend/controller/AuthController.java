package xyz.genzsage.genzsagebackend.controller;


import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.genzsage.genzsagebackend.dto.*;
import xyz.genzsage.genzsagebackend.dto.GlobalResponseDto.GlobalResponseDtoBuilder;
import xyz.genzsage.genzsagebackend.model.Sage;
import xyz.genzsage.genzsagebackend.service.AuthService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    public GlobalResponseDto<AuthResponse> register(@RequestBody RegisterSageRequest request) {
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
    public GlobalResponseDto<AuthResponse> login(@RequestBody LoginRequest request){
        AuthResponse authResponse=authService.login(request);
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
        AuthResponse authResponse = authService.refreshToken(request.getRefreshToken());
        return GlobalResponseDto.<AuthResponse>builder()
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .message("Token refreshed successfully")
                .data(authResponse)
                .errors(null)
                .build();
    }

}

