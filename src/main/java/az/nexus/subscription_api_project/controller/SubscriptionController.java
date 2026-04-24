package az.nexus.subscription_api_project.controller;

import az.nexus.subscription_api_project.dto.response.ApiResponse;
import az.nexus.subscription_api_project.dto.response.SubscriptionResponse;
import az.nexus.subscription_api_project.entity.User;
import az.nexus.subscription_api_project.enums.PlanType;
import az.nexus.subscription_api_project.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @GetMapping("/status")
    public ResponseEntity<ApiResponse<SubscriptionResponse>> getStatus(@AuthenticationPrincipal User user) {
        SubscriptionResponse status = subscriptionService.getUserSubscriptionStatus(user);
        return ResponseEntity.ok(
                ApiResponse.success(status, "Subscription status retrieved successfully")
        );
    }

    @GetMapping("/premium-content")
    @PreAuthorize("hasAnyRole('PRO', 'ENTERPRISE')")
    public ResponseEntity<ApiResponse<String>> getPremiumData() {
        return ResponseEntity.ok(ApiResponse.success("Exclusive Premium Movie Content", "Access granted"));
    }

    @GetMapping("/enterprise-dashboard")
    @PreAuthorize("hasRole('ENTERPRISE')")
    public ResponseEntity<ApiResponse<String>> getEnterpriseData() {
        return ResponseEntity.ok(
                ApiResponse.success(
                        "Enterprise Dashboard: Exclusive features activated.",
                        "Enterprise access granted"
                )
        );
    }

    @PostMapping("/upgrade")
    @PreAuthorize("isAuthenticated()") // Yalnız login olanlar müraciət edə bilər
    public ResponseEntity<String> upgrade(
            @RequestParam Long userId,
            @RequestParam PlanType newPlan) {

        subscriptionService.upgradeSubscription(userId, newPlan);
        return ResponseEntity.ok("Successfully upgraded to " + newPlan);
    }
}