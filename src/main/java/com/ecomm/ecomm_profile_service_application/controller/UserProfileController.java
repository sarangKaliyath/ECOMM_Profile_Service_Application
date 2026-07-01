package com.ecomm.ecomm_profile_service_application.controller;

import com.ecomm.ecomm_profile_service_application.dto.internal.CreateProfileRequestDto;
import com.ecomm.ecomm_profile_service_application.dto.request.UpdateProfileRequestDto;
import com.ecomm.ecomm_profile_service_application.dto.response.UserProfileResponseDto;
import com.ecomm.ecomm_profile_service_application.model.UserProfile;
import com.ecomm.ecomm_profile_service_application.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile/me")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping
    public ResponseEntity<UserProfileResponseDto> getProfile(Long authUserId) {
        UserProfileResponseDto responseDto = userProfileService.getProfile(authUserId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }


    @PatchMapping
    public ResponseEntity<UserProfileResponseDto> updateProfile(@RequestBody Long authUserId, UpdateProfileRequestDto requestDto) {
        UserProfileResponseDto responseDto = userProfileService.updateProfile(authUserId, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

}
