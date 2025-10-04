package org.dd.bre.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class ErrorDetails {
    private String message;
    private Integer code;
    private LocalDateTime timestamp;
}
