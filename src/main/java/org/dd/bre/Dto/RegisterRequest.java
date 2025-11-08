package org.dd.bre.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank
    private String fullName;

    @Email
    private String email;

    @NotBlank
    private String username;

    @Pattern(
            regexp = "^\\+?[0-9]{7,15}$",
            message = "Invalid phone number format"
    )
    private String phone;

    @Pattern(
            regexp = "^\\+?[0-9]{7,15}$",
            message = "Invalid phone number format"
    )
    private String password;

    @Pattern(
            regexp = "^\\+?[0-9]{7,15}$",
            message = "Invalid phone number format"
    )
    private String confirmPassword;
}
