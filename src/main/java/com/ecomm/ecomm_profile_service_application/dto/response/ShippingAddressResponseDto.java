package com.ecomm.ecomm_profile_service_application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippingAddressResponseDto {
    private Long id;

    private String firstName;
    private String lastName;
    private String phoneNumber;

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String pincode;
}
