package az.nexus.subscription_api_project.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record RegisterRequest(
        @NotBlank(message = "Full name cannot be blank")
        String fullName,

        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Please provide a valid email address")
        String email,

        @NotBlank(message = "Password cannot be blank")
        @Size(min = 8, message = "Password must be at least 8 characters long")
        @Pattern(
                regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$",
                message = "Password must contain at least one digit, one lowercase, and one uppercase letter"
        )
        String password
) {}