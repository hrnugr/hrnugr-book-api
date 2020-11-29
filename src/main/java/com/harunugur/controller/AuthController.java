package com.harunugur.controller;

import com.harunugur.dto.request.SigninDto;
import com.harunugur.dto.response.MessageDto;
import com.harunugur.dto.request.SignupDto;
import com.harunugur.dto.response.JwtDto;
import com.harunugur.entity.auth.Role;
import com.harunugur.entity.auth.User;
import com.harunugur.entity.enums.RoleType;
import com.harunugur.handle.exception.DataNotFoundException;
import com.harunugur.repository.auth.RoleRepository;
import com.harunugur.repository.auth.UserRepository;
import com.harunugur.security.jwt.JwtUtils;
import com.harunugur.security.service.UserDetailsImpl;
import com.harunugur.security.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupDto signupDto) {

        if (userRepository.existsByEmail(signupDto.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageDto("Error: Email is already in use!"));
        }

        if (userRepository.existsByUsername(signupDto.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageDto("Error: Username is already taken!"));
        }

        Set<String> strRoles = signupDto.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(RoleType.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(RoleType.ROLE_ADMIN)
                                .orElseThrow(() -> new DataNotFoundException(Messages.ROLE_NOT_FOUND));
                        roles.add(adminRole);

                        break;
                    case "editor":
                        Role ediRole = roleRepository.findByName(RoleType.ROLE_EDITOR)
                                .orElseThrow(() -> new DataNotFoundException(Messages.ROLE_NOT_FOUND));
                        roles.add(ediRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(RoleType.ROLE_USER)
                                .orElseThrow(() -> new DataNotFoundException(Messages.ROLE_NOT_FOUND));
                        roles.add(userRole);
                }
            });
        }
        // Create new user's account
        User user = User.builder()
                    .username(signupDto.getUsername())
                    .email(signupDto.getEmail())
                    .password(encoder.encode(signupDto.getPassword()))
                    .roles(roles)
                    .build();

        userRepository.save(user);

        return ResponseEntity.ok(new MessageDto("User registered successfully!"));
    }


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SigninDto signinDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signinDto.getUsername(), signinDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                 JwtDto.builder()
                         .token(jwt)
                         .username(userDetails.getUsername())
                         .email(userDetails.getEmail())
                         .id(userDetails.getId())
                         .roles(roles).build()
        );
    }
}
