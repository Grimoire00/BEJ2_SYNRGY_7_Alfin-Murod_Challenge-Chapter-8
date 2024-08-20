package com.binar.batch7.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotBlank(message = "Must not empty")
    private String username;
    @NotBlank(message = "Must not empty")
    private String emailAddress;
    @NotBlank(message = "Must not empty")
    private String password;
}
