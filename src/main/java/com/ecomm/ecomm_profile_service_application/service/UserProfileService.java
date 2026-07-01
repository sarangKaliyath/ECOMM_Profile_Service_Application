package com.ecomm.ecomm_profile_service_application.service;

import com.ecomm.ecomm_profile_service_application.dto.internal.CreateProfileRequestDto;
import com.ecomm.ecomm_profile_service_application.dto.request.UpdateProfileRequestDto;
import com.ecomm.ecomm_profile_service_application.dto.response.UserProfileResponseDto;
import com.ecomm.ecomm_profile_service_application.model.UserProfile;

public interface UserProfileService {

    UserProfileResponseDto getProfile(Long authUserId);

    UserProfileResponseDto updateProfile(Long authUserId, UpdateProfileRequestDto updateProfileRequestDto);

    void createProfile(CreateProfileRequestDto createProfileRequestDto);
}
