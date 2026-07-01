package com.ecomm.ecomm_profile_service_application.mapper;

import com.ecomm.ecomm_profile_service_application.dto.request.CreateAddressRequestDto;
import com.ecomm.ecomm_profile_service_application.dto.response.AddressResponseDto;
import com.ecomm.ecomm_profile_service_application.dto.request.UpdateAddressRequestDto;
import com.ecomm.ecomm_profile_service_application.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressResponseDto toResponse(Address address){
        AddressResponseDto addressResponseDto = new AddressResponseDto();

        addressResponseDto.setId(address.getId());
        addressResponseDto.setAddressLine1(address.getAddressLine1());
        addressResponseDto.setAddressLine2(address.getAddressLine2());
        addressResponseDto.setCity(address.getCity());
        addressResponseDto.setState(address.getState());
        addressResponseDto.setPincode(address.getPincode());
        addressResponseDto.setCountry(address.getCountry());

        addressResponseDto.setAddressType(address.getAddressType());
        addressResponseDto.setIsDefault(address.getIsDefault());

        return  addressResponseDto;
    }

    public Address toEntity(CreateAddressRequestDto requestDto) {
        Address address = new Address();

        address.setAddressLine1(requestDto.getAddressLine1());
        address.setAddressLine2(requestDto.getAddressLine2());
        address.setCity(requestDto.getCity());
        address.setState(requestDto.getState());
        address.setCountry(requestDto.getCountry());
        address.setPincode(requestDto.getPincode());

        address.setAddressType(requestDto.getAddressType());
        address.setIsDefault(requestDto.getIsDefault());

        return address;
    }

    public void updateEntity(Address address, UpdateAddressRequestDto updateAddressRequestDto) {
        if (updateAddressRequestDto.getAddressLine1() != null) {
            address.setAddressLine1(updateAddressRequestDto.getAddressLine1());
        }
        if (updateAddressRequestDto.getAddressLine2() != null) {
            address.setAddressLine2(updateAddressRequestDto.getAddressLine2());
        }
        if (updateAddressRequestDto.getCity() != null) {
            address.setCity(updateAddressRequestDto.getCity());
        }
        if (updateAddressRequestDto.getState() != null) {
            address.setState(updateAddressRequestDto.getState());
        }
        if (updateAddressRequestDto.getCountry() != null) {
            address.setCountry(updateAddressRequestDto.getCountry());
        }
        if (updateAddressRequestDto.getPincode() != null) {
            address.setPincode(updateAddressRequestDto.getPincode());
        }
        if (updateAddressRequestDto.getAddressType() != null) {
            address.setAddressType(updateAddressRequestDto.getAddressType());
        }
        if (updateAddressRequestDto.getIsDefault() != null) {
            address.setIsDefault(updateAddressRequestDto.getIsDefault());
        }
    }
}