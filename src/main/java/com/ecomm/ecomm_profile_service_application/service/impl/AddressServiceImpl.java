package com.ecomm.ecomm_profile_service_application.service.impl;

import com.ecomm.ecomm_profile_service_application.dto.request.CreateAddressRequestDto;
import com.ecomm.ecomm_profile_service_application.dto.request.UpdateAddressRequestDto;
import com.ecomm.ecomm_profile_service_application.dto.response.AddressResponseDto;
import com.ecomm.ecomm_profile_service_application.exception.AddressNotFoundException;
import com.ecomm.ecomm_profile_service_application.exception.DefaultAddressCannotBeDeletedException;
import com.ecomm.ecomm_profile_service_application.exception.ProfileNotFoundException;
import com.ecomm.ecomm_profile_service_application.mapper.AddressMapper;
import com.ecomm.ecomm_profile_service_application.model.Address;
import com.ecomm.ecomm_profile_service_application.model.UserProfile;
import com.ecomm.ecomm_profile_service_application.repository.AddressRepository;
import com.ecomm.ecomm_profile_service_application.repository.UserProfileRepository;
import com.ecomm.ecomm_profile_service_application.service.AddressService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserProfileRepository userProfileRepository;
    private final AddressMapper addressMapper;

    @Override
    public List<AddressResponseDto> getAddresses(Long authUserId) {
        UserProfile userProfile = getUserProfile(authUserId);

        return addressRepository.findByUserProfile(userProfile)
                .stream()
                .map(addressMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public AddressResponseDto createAddress(Long authUserId, CreateAddressRequestDto requestDto) {
        UserProfile userProfile = getUserProfile(authUserId);
        Address address = addressMapper.toEntity(requestDto);

        address.setUserProfile(userProfile);

        if(Boolean.TRUE.equals(requestDto.getIsDefault())){
            clearDefaultAddress(userProfile);
            address.setIsDefault(true);
        }

        addressRepository.save(address);

        return addressMapper.toResponse(address);
    }

    @Override
    @Transactional
    public AddressResponseDto updateAddress(Long authUserId, Long addressId, UpdateAddressRequestDto requestDto) {
        UserProfile userProfile = getUserProfile(authUserId);
        Address address = getAddress(addressId, userProfile);

        boolean makeDefault = Boolean.TRUE.equals(requestDto.getIsDefault());
        addressMapper.updateEntity(address, requestDto);

        if (makeDefault) {
            clearDefaultAddress(userProfile);
            address.setIsDefault(true);
        }

        addressRepository.save(address);

        return addressMapper.toResponse(address);
    }

    @Override
    @Transactional
    public void deleteAddress(Long authUserId, Long addressId) {
        UserProfile userProfile = getUserProfile(authUserId);
        Address address = getAddress(addressId, userProfile);
        if(address.getIsDefault()) throw new DefaultAddressCannotBeDeletedException("Default address cannot be delete.");
        addressRepository.delete(address);
    }

    @Override
    @Transactional
    public AddressResponseDto setDefaultAddress(Long authUserId, Long addressId) {
        UserProfile userProfile = getUserProfile(authUserId);
        Address address = getAddress(addressId, userProfile);
        clearDefaultAddress(userProfile);
        address.setIsDefault(true);
        addressRepository.save(address);
        return addressMapper.toResponse(address);
    }

    // ************************************** Helper methods ******************************************

    private UserProfile getUserProfile(Long authUserId) {
        return userProfileRepository.findByAuthUserId(authUserId)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found by authUserId: " + authUserId));
    }

    private Address getAddress(
            Long addressId,
            UserProfile userProfile) {

        return addressRepository
                .findByIdAndUserProfile(addressId, userProfile)
                .orElseThrow(() ->
                        new AddressNotFoundException(
                                "Address not found: " + addressId
                        ));
    }

    private void clearDefaultAddress(UserProfile profile) {

        addressRepository.findByUserProfileAndIsDefaultTrue(profile)
                .ifPresent(address -> {
                    address.setIsDefault(false);
                    addressRepository.save(address);
                });
    }
}
