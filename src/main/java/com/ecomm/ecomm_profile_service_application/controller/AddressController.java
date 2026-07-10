package com.ecomm.ecomm_profile_service_application.controller;

import com.ecomm.ecomm_profile_service_application.dto.request.CreateAddressRequestDto;
import com.ecomm.ecomm_profile_service_application.dto.request.UpdateAddressRequestDto;
import com.ecomm.ecomm_profile_service_application.dto.response.AddressResponseDto;
import com.ecomm.ecomm_profile_service_application.dto.response.OrderAddressResponseDto;
import com.ecomm.ecomm_profile_service_application.security.AuthenticatedUser;
import com.ecomm.ecomm_profile_service_application.security.annotation.CurrentUser;
import com.ecomm.ecomm_profile_service_application.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile/me/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<List<AddressResponseDto>> getAddress(@CurrentUser AuthenticatedUser user) {
        return ResponseEntity.ok(addressService.getAddresses(user.getUserId()));
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<OrderAddressResponseDto> getAddress(
            @CurrentUser AuthenticatedUser user,
            @PathVariable Long addressId) {

        return ResponseEntity.ok(
                addressService.getAddress(user.getUserId(), addressId)
        );
    }

    @PostMapping
    public ResponseEntity<AddressResponseDto> createAddress(@CurrentUser AuthenticatedUser user, @RequestBody CreateAddressRequestDto requestDto) {
        AddressResponseDto responseDto = addressService.createAddress(user.getUserId(), requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PatchMapping("/{addressId}")
    public ResponseEntity<AddressResponseDto> updateAddress(@CurrentUser AuthenticatedUser user, @PathVariable Long addressId, @RequestBody UpdateAddressRequestDto requestDto) {
        AddressResponseDto responseDto = addressService.updateAddress(user.getUserId(), addressId, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<HttpStatus> deleteAddress(@CurrentUser AuthenticatedUser user, @PathVariable Long addressId) {
        addressService.deleteAddress(user.getUserId(), addressId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressResponseDto> setDefaultAddress(@CurrentUser AuthenticatedUser user, @PathVariable Long addressId) {
        AddressResponseDto responseDto = addressService.setDefaultAddress(user.getUserId(), addressId);
        return ResponseEntity.ok(responseDto);
    }
}
