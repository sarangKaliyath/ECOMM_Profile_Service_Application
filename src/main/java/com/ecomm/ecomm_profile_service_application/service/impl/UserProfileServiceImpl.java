package com.ecomm.ecomm_profile_service_application.service.impl;

import com.ecomm.ecomm_profile_service_application.dto.internal.CreateProfileRequestDto;
import com.ecomm.ecomm_profile_service_application.dto.request.UpdateProfileRequestDto;
import com.ecomm.ecomm_profile_service_application.dto.response.AddressResponseDto;
import com.ecomm.ecomm_profile_service_application.dto.response.UserProfileResponseDto;
import com.ecomm.ecomm_profile_service_application.exception.DuplicateProfileException;
import com.ecomm.ecomm_profile_service_application.exception.ProfileNotFoundException;
import com.ecomm.ecomm_profile_service_application.mapper.AddressMapper;
import com.ecomm.ecomm_profile_service_application.mapper.UserProfileMapper;
import com.ecomm.ecomm_profile_service_application.model.Address;
import com.ecomm.ecomm_profile_service_application.model.UserProfile;
import com.ecomm.ecomm_profile_service_application.repository.AddressRepository;
import com.ecomm.ecomm_profile_service_application.repository.UserProfileRepository;
import com.ecomm.ecomm_profile_service_application.service.UserProfileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final AddressRepository addressRepository;
    private final UserProfileMapper userProfileMapper;
    private final AddressMapper addressMapper;

    @Override
    public UserProfileResponseDto getProfile(Long authUserId) {
        UserProfile userProfile = userProfileRepository.findByAuthUserId(authUserId)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found by authUserId: " + authUserId));

        List<Address> addresses = addressRepository.findByUserProfile(userProfile);
        List<AddressResponseDto> addressResponse = addresses.stream().map(addressMapper::toResponse).toList();

        return userProfileMapper.toResponse(userProfile, addressResponse);
    }

    @Override
    @Transactional
    public UserProfileResponseDto updateProfile(Long authUserId, UpdateProfileRequestDto updateProfileRequestDto) {
        UserProfile userProfile = userProfileRepository.findByAuthUserId(authUserId)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found by authUserId: " + authUserId));

        userProfileMapper.updateEntity(userProfile, updateProfileRequestDto);
        userProfileRepository.save(userProfile);

        return getProfile(authUserId);
    }

    @Override
    public void createProfile(CreateProfileRequestDto requestDto) {
        boolean doesUserExist = userProfileRepository.existsByAuthUserId(requestDto.getAuthUserId());

        if (doesUserExist)
            throw new DuplicateProfileException("Profile already exists for authUserId: " + requestDto.getAuthUserId());

        UserProfile userProfile = userProfileMapper.toEntity(requestDto);

        userProfileRepository.save(userProfile);
    }
}
