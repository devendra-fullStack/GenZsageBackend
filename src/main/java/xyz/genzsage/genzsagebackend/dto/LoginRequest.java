package xyz.genzsage.genzsagebackend.dto;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginRequest {
    private String identifier;
    private String password;
}
