package com.ecomm.ecomm_profile_service_application.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Address extends BaseModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id", nullable = false)
    private UserProfile userProfile;

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String pincode;
    @Enumerated(EnumType.STRING)
    private AddressType addressType;
    private Boolean isDefault;
}
