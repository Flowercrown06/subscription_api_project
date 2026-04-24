package az.nexus.subscription_api_project.dto.response;

import lombok.Builder;

@Builder
public record LoginResponse(
        String token,
        String email,
        String role
) {}