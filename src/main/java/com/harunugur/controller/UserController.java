package com.harunugur.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_EDITOR') or hasRole('ROLE_ADMIN')")
    public String getUser(@PathVariable Long id) {
        return "User Content.";
    }
}
