package com.ecomm.ecomm_profile_service_application.dto.request;

import com.ecomm.ecomm_profile_service_application.model.AddressType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAddressRequestDto {

    @NotBlank
    @Size(max = 255)
    private String addressLine1;

    private String addressLine2;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String country;

    @NotBlank
    @Pattern(regexp = "\\d{6}", message = "Pincode must be 6 digits")
    private String pincode;

    @NotNull
    private AddressType addressType;

    @NotNull
    private Boolean isDefault;
}