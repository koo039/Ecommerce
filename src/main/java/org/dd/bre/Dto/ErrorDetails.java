package org.dd.bre.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class ErrorDetails {
    private final String message;
    private final Integer code;
    private final LocalDateTime timestamp;
    private String path;
}
