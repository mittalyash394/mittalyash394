package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {


    @NotEmpty(message = "The emailId cannot be empty")
    @NotNull(message = "The emailId cannot be null")
    @Pattern(regexp = "^(.+)@(.+)$", message = "The email should be in a proper format")
    private String emailId;

    @NotEmpty(message = "The password cannot be empty")
    @NotNull(message = "The password cannot be null")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "The password should be in proper format")
    private String password;


}
