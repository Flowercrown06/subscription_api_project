package az.nexus.subscription_api_project.controller;

import az.nexus.subscription_api_project.dto.request.LoginRequest;
import az.nexus.subscription_api_project.dto.request.RegisterRequest;
import az.nexus.subscription_api_project.dto.response.ApiResponse;
import az.nexus.subscription_api_project.dto.response.LoginResponse;
import az.nexus.subscription_api_project.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

 import java.time.LocalDateTime;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);

        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("Registration completed successfully")
                .timestamp(LocalDateTime.now())
                .build());
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> authenticate(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);

        return ResponseEntity.ok(ApiResponse.<LoginResponse>builder()
                .success(true)
                .message("Login successful")
                .data(response)
                .timestamp(LocalDateTime.now())
                .build());
    }
}