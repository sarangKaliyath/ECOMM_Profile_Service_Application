package com.ecomm.ecomm_profile_service_application.dto.internal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreatedEvent {
    private Long authUserId;
    private String firstName;
    private String lastName;
}
