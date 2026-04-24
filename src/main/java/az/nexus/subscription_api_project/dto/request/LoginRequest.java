package az.nexus.subscription_api_project.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @Email(message = "Please enter the correct email format.")
        @NotBlank(message = "Email cannot be blank")
        String email,

        @NotBlank(message = "Password cannot be blank")
        String password
) {}