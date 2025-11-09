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

    @Pattern(regexp = "[A-Za-z0-9!@#$%^&*_]{8,15}", message = "Password must be 8-15 characters in length and can include A-Z, a-z, 0-9, or special characters !@#$%^&*_")
    private String password;

    @Pattern(regexp = "[A-Za-z0-9!@#$%^&*_]{8,15}", message = "Confirm password must match requirements")
    private String confirmPassword;
}
