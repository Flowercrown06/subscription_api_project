package az.nexus.subscription_api_project.entity;

import az.nexus.subscription_api_project.enums.PlanType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PlanType planType;

    private LocalDateTime startDate;
    private LocalDateTime expiryDate;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}