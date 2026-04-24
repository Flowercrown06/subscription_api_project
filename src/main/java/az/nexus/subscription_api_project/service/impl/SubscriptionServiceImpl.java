package az.nexus.subscription_api_project.service.impl;

import az.nexus.subscription_api_project.dto.response.SubscriptionResponse;
import az.nexus.subscription_api_project.entity.Subscription;
import az.nexus.subscription_api_project.entity.User;
import az.nexus.subscription_api_project.enums.PlanType;
import az.nexus.subscription_api_project.enums.Role;
import az.nexus.subscription_api_project.exception.ResourceNotFoundException;
import az.nexus.subscription_api_project.mapper.SubscriptionMapper;
import az.nexus.subscription_api_project.repository.SubscriptionRepository;
import az.nexus.subscription_api_project.repository.UserRepository;
import az.nexus.subscription_api_project.service.SubscriptionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final SubscriptionMapper subscriptionMapper;

    @Override
    public SubscriptionResponse getUserSubscriptionStatus(User user) {
        Subscription sub = subscriptionRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Subscription not found"));


        return subscriptionMapper.toResponse(sub);
    }

    @Override
    public void createDefaultSubscription(User user) {
        Subscription subscription = Subscription.builder()
                .planType(PlanType.FREE)
                .startDate(LocalDateTime.now())
                .expiryDate(LocalDateTime.now().plusMonths(1))
                .user(user)
                .build();
        subscriptionRepository.save(subscription);
    }

    @Transactional
    public void upgradeSubscription(Long userId, PlanType newPlan) {
        // 1. İstifadəçini tapırıq
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Mövcud abunəliyi tapırıq və ya yenisini yaradırıq
        Subscription subscription = subscriptionRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Subscription newSub = new Subscription();
                    newSub.setUser(user);
                    return newSub;
                });

        // 3. Tarixi hesablayırıq (əgər hələ bitməyibsə üzərinə 1 ay gəlirik)
        LocalDateTime startDate = (subscription.getExpiryDate() != null &&
                subscription.getExpiryDate().isAfter(LocalDateTime.now()))
                ? subscription.getExpiryDate()
                : LocalDateTime.now();

        subscription.setPlanType(newPlan);
        subscription.setExpiryDate(startDate.plusMonths(1));
        subscriptionRepository.save(subscription);

        // 4. Rol Mapping Məntiqi (FREE -> USER, digərləri eynilə)
        try {
            Role newRole = (newPlan == PlanType.FREE) ? Role.USER : Role.valueOf(newPlan.name());
            user.setRole(newRole);
            userRepository.save(user);
        } catch (IllegalArgumentException e) {
            log.error("Role mapping error for plan: {}", newPlan);
            throw new RuntimeException("Mapping error: Plan " + newPlan + " has no matching Role");
        }

        log.info("Subscription upgraded: User {} is now {}", user.getEmail(), newPlan);
    }
}