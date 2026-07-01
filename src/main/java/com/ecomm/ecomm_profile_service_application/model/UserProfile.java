package com.ecomm.ecomm_profile_service_application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class UserProfile extends BaseModel {
    @Column(name = "auth_user_id", nullable = false, unique = true)
    private Long authUserId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Date dateOfBirth;
    private String profilePictureUrl;
}
