package com.harunugur.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SigninDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
