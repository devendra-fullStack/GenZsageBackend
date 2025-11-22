package xyz.genzsage.genzsagebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * DTO for refresh token requests.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RefreshTokenRequest {
    private String refreshToken;
}
