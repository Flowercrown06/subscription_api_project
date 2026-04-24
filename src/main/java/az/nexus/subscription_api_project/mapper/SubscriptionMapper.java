package az.nexus.subscription_api_project.mapper;


import az.nexus.subscription_api_project.dto.response.SubscriptionResponse;
import az.nexus.subscription_api_project.entity.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, imports = LocalDateTime.class)
public interface SubscriptionMapper {

    @Mapping(target = "planType", expression = "java(subscription.getPlanType().name())")
    @Mapping(target = "isActive", expression = "java(subscription.getExpiryDate().isAfter(LocalDateTime.now()))")
    SubscriptionResponse toResponse(Subscription subscription);
}