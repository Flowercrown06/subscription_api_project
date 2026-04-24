package az.nexus.subscription_api_project.dto.response;

import lombok.Builder;

@Builder
public record UserResponse(
        Long id,
        String email,
        String fullName,
        String role
) {}