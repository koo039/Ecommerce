package org.dd.bre.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserProfileDto {

    @NotNull
    String   firstName;

    @NotNull
    String  lastName;

    @NotNull
    @Email
    String  email;

    @Pattern(
            regexp = "^\\+?[0-9]{7,15}$",
            message = "Invalid phone number format"
    )
    @NotNull
    String phoneNumber;
}
