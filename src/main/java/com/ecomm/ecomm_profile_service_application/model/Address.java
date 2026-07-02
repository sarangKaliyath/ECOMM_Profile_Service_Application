package com.ecomm.ecomm_profile_service_application.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(indexes = {@Index(name = "idx_address_profile", columnList = "user_profile_id")})
public class Address extends BaseModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id", nullable = false)
    private UserProfile userProfile;

    @NotBlank(message = "Address line 1 cannot be blank")
    private String addressLine1;
    private String addressLine2;

    @NotBlank(message = "City cannot be blank")
    private String city;

    @NotBlank(message = "State cannot be blank")
    private String state;

    @NotBlank(message = "Country cannot be blank")
    private String country;

    @NotBlank(message = "Pincode cannot be blank")
    private String pincode;

    @Enumerated(EnumType.STRING)
    private AddressType addressType;
    private Boolean isDefault;
}
