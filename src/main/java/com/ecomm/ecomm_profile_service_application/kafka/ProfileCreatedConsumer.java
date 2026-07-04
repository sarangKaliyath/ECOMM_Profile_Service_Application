package com.ecomm.ecomm_profile_service_application.kafka;

import com.ecomm.ecomm_profile_service_application.dto.internal.CreateProfileRequestDto;
import com.ecomm.ecomm_profile_service_application.dto.internal.UserCreatedEvent;
import com.ecomm.ecomm_profile_service_application.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileCreatedConsumer {

    private final UserProfileService userProfileService;

    @KafkaListener(
            topics = "user-created",
            groupId = "profile-service"
    )
    public void consume(UserCreatedEvent event) {

        CreateProfileRequestDto dto =
                new CreateProfileRequestDto();

        dto.setAuthUserId(event.getAuthUserId());
        dto.setFirstName(event.getFirstName());
        dto.setLastName(event.getLastName());

        userProfileService.createProfile(dto);
    }
}