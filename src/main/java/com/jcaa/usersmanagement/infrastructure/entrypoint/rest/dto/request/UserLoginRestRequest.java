package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserLoginRestRequest(
        @NotBlank(message = "email must not be blank")
        @Email(message = "email must be a valid email address")
        String email,

        @NotBlank(message = "password must not be blank")
        String password
) {}