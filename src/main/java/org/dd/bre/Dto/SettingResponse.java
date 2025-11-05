package org.dd.bre.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SettingResponse {
    @NotBlank
    private boolean isEmailEnabled;
    @NotBlank
    private boolean isPhoneEnabled;
}
