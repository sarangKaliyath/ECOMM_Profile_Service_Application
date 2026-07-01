package com.ecomm.ecomm_profile_service_application.dto.request;

import com.ecomm.ecomm_profile_service_application.model.AddressType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAddressRequestDto {
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String pincode;
    private AddressType addressType;
    private Boolean isDefault;
}
