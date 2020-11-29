package com.harunugur.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtDto {

    private String token;
    @JsonIgnore
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
}
