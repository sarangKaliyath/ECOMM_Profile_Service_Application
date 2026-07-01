package com.ecomm.ecomm_profile_service_application.repository;

import com.ecomm.ecomm_profile_service_application.model.Address;
import com.ecomm.ecomm_profile_service_application.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUserProfile(UserProfile userProfile);

    Optional<Address> findByIdAndUserProfile(long id, UserProfile userProfile);

    boolean existsByIdAndUserProfile(long id, UserProfile userProfile);

    Optional<Address> findByUserProfileAndIsDefaultTrue(
            UserProfile userProfile);
}
