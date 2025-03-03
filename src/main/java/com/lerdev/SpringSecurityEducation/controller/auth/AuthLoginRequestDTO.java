package com.lerdev.SpringSecurityEducation.controller.auth;


import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequestDTO (@NotBlank String username, @NotBlank String password) {
}
