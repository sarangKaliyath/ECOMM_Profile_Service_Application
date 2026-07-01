package com.ecomm.ecomm_profile_service_application.mapper;

import com.ecomm.ecomm_profile_service_application.dto.internal.CreateProfileRequestDto;
import com.ecomm.ecomm_profile_service_application.dto.request.UpdateProfileRequestDto;
import com.ecomm.ecomm_profile_service_application.dto.response.AddressResponseDto;
import com.ecomm.ecomm_profile_service_application.dto.response.UserProfileResponseDto;
import com.ecomm.ecomm_profile_service_application.model.UserProfile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserProfileMapper {

    public UserProfileResponseDto toResponse(
            UserProfile userProfile,
            List<AddressResponseDto> addresses) {

        UserProfileResponseDto responseDto = new UserProfileResponseDto();

        responseDto.setId(userProfile.getId());
        responseDto.setFirstName(userProfile.getFirstName());
        responseDto.setLastName(userProfile.getLastName());
        responseDto.setPhoneNumber(userProfile.getPhoneNumber());
        responseDto.setDateOfBirth(userProfile.getDateOfBirth());
        responseDto.setProfilePictureUrl(userProfile.getProfilePictureUrl());
        responseDto.setAddresses(addresses);

        return responseDto;
    }

    public UserProfile toEntity(CreateProfileRequestDto requestDto) {

        UserProfile userProfile = new UserProfile();

        userProfile.setAuthUserId(requestDto.getAuthUserId());
        userProfile.setFirstName(requestDto.getFirstName());
        userProfile.setLastName(requestDto.getLastName());

        return userProfile;
    }

    public void updateEntity(
            UserProfile userProfile,
            UpdateProfileRequestDto requestDto) {

        if (requestDto.getFirstName() != null) {
            userProfile.setFirstName(requestDto.getFirstName());
        }

        if (requestDto.getLastName() != null) {
            userProfile.setLastName(requestDto.getLastName());
        }

        if (requestDto.getPhoneNumber() != null) {
            userProfile.setPhoneNumber(requestDto.getPhoneNumber());
        }

        if (requestDto.getDateOfBirth() != null) {
            userProfile.setDateOfBirth(requestDto.getDateOfBirth());
        }

        if (requestDto.getProfilePictureUrl() != null) {
            userProfile.setProfilePictureUrl(requestDto.getProfilePictureUrl());
        }
    }
}