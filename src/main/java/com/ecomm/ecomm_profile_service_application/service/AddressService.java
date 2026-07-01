package com.ecomm.ecomm_profile_service_application.service;

import com.ecomm.ecomm_profile_service_application.dto.request.CreateAddressRequestDto;
import com.ecomm.ecomm_profile_service_application.dto.request.UpdateAddressRequestDto;
import com.ecomm.ecomm_profile_service_application.dto.response.AddressResponseDto;
import com.ecomm.ecomm_profile_service_application.model.Address;

import java.util.List;

public interface AddressService {

    List<AddressResponseDto> getAddresses(Long authUserId);

    AddressResponseDto createAddress(Long authUserId, CreateAddressRequestDto requestDto);

    AddressResponseDto updateAddress(Long authUserId, Long addressId, UpdateAddressRequestDto requestDto);

    void deleteAddress(Long authUserId, Long addressId);

    AddressResponseDto setDefaultAddress(Long authUserId, Long addressId);
}
