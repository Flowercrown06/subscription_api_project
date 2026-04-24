package az.nexus.subscription_api_project.service;

import az.nexus.subscription_api_project.dto.response.SubscriptionResponse;
import az.nexus.subscription_api_project.entity.User;

public interface SubscriptionService {
    void createDefaultSubscription(User user);
    SubscriptionResponse getUserSubscriptionStatus(User user);
}
