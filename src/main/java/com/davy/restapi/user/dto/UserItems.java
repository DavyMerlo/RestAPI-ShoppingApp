package com.davy.restapi.user.dto;

import com.davy.restapi.user.enums.Role;

public record UserItems(
        Long id,
        String firstName,
        String lastName,
        String email,
        Role role
) {
}