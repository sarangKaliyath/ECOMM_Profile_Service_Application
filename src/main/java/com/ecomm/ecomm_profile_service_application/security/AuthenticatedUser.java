package com.ecomm.ecomm_profile_service_application.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AuthenticatedUser {

    private final Long userId;

    private final String email;

    private final List<String> roles;
}