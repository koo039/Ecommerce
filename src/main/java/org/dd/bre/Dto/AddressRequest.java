package org.dd.bre.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AddressRequest {

    @NotBlank
    private String addressLine1;

    private String addressLine2;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String country;

    @NotBlank
    private String postalCode;

    @Pattern(regexp = "^[+0-9]{6,15}$", message = "Invalid phone number")
    private String phoneNumber;

    private String label;
}
