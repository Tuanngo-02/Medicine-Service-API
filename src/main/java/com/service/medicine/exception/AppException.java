package com.service.medicine.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppException extends RuntimeException {
    private ErrorCode errorCode;
}
