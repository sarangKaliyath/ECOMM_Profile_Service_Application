package com.ecomm.ecomm_profile_service_application.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UpdateProfileRequestDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Date dateOfBirth;
    private String profilePictureUrl;
}
