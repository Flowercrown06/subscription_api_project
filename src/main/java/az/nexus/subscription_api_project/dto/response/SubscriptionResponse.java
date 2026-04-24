package az.nexus.subscription_api_project.dto.response;



import java.time.LocalDateTime;
public record SubscriptionResponse(
        String planType,
        boolean isActive,
        LocalDateTime expiryDate
) {}