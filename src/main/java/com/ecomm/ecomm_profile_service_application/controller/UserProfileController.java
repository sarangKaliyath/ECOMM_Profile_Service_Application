package com.ecomm.ecomm_profile_service_application.controller;

import com.ecomm.ecomm_profile_service_application.dto.request.UpdateProfileRequestDto;
import com.ecomm.ecomm_profile_service_application.dto.response.UserProfileResponseDto;
import com.ecomm.ecomm_profile_service_application.security.AuthenticatedUser;
import com.ecomm.ecomm_profile_service_application.security.annotation.CurrentUser;
import com.ecomm.ecomm_profile_service_application.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile/me")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping
    public ResponseEntity<UserProfileResponseDto> getProfile(@CurrentUser AuthenticatedUser user) {
        return ResponseEntity.ok(userProfileService.getProfile(user.getUserId()));
    }

    @PatchMapping
    public ResponseEntity<UserProfileResponseDto> updateProfile(@CurrentUser AuthenticatedUser user, @RequestBody UpdateProfileRequestDto requestDto) {
        return ResponseEntity.ok(userProfileService.updateProfile(user.getUserId(), requestDto));
    }
}