package xyz.genzsage.genzsagebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GlobalResponseDto<T> {
    private boolean success;// true if request succeeded, false otherwise
    private int statusCode;
    private String message;        // descriptive message
    private T data;                // actual response payload, null on error
    private List<String> errors;   // list of error messages, null or empty on success
}
