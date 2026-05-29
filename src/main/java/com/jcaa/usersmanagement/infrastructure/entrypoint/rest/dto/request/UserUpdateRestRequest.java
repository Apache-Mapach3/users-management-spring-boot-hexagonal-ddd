package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request;

public record UserUpdateRestRequest(
        String name,
        String email,
        String role,
        String status
) {}
