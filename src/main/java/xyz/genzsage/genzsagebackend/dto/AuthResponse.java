package xyz.genzsage.genzsagebackend.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;


@ToString
@Data
@AllArgsConstructor
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
}