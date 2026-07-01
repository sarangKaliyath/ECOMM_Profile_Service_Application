package com.ecomm.ecomm_profile_service_application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserProfileResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Date dateOfBirth;
    private String profilePictureUrl;

    private List<AddressResponseDto> addresses;
}
