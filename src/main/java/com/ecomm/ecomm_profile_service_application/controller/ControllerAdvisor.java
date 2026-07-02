package com.ecomm.ecomm_profile_service_application.controller;

import com.ecomm.ecomm_profile_service_application.exception.AddressNotFoundException;
import com.ecomm.ecomm_profile_service_application.exception.ProfileNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.DestroyFailedException;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(ProfileNotFoundException.class)
    public ResponseEntity<String> handleProfileNotFoundException(ProfileNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleDuplicateProfileException(Exception e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<String> handleAddressNotFoundException(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(DestroyFailedException.class)
    public ResponseEntity<String> handleDefaultAddressCannotBeDeletedException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
