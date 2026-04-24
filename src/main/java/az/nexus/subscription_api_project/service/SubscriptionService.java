package az.nexus.subscription_api_project.service;

import az.nexus.subscription_api_project.dto.response.SubscriptionResponse;
import az.nexus.subscription_api_project.entity.User;
import az.nexus.subscription_api_project.enums.PlanType;

public interface SubscriptionService {
    void createDefaultSubscription(User user);
    SubscriptionResponse getUserSubscriptionStatus(User user);
    void upgradeSubscription(Long userId, PlanType newPlan);

}
