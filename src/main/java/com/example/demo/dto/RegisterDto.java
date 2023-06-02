package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

    @NotEmpty(message = "The first name cannot be empty")
    @NotNull(message = "The first name cannot be null")
    @Pattern(regexp = "[A-Z][a-z]*", message = "The first name should be in proper format")
    private String firstName;

    @NotEmpty(message = "The last name cannot be empty")
    @NotNull(message = "The last name cannot be null")
    @Pattern(regexp = "[A-Z][a-z]*", message = "The last name should be in proper format")
    private String lastName;


    @NotEmpty(message = "The emailId cannot be empty")
    @NotNull(message = "The emailId cannot be null")
    @Email(message = "This should be an email")
    @Pattern(regexp = "^(.+)@(.+)$", message = "The email should be in a proper format")
    private String emailId;

    @NotEmpty(message = "The password cannot be empty")
    @NotNull(message = "The password cannot be null")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "The password should be in proper format")
    private String password;

    @NotEmpty(message = "The confirm Password cannot be empty")
    @NotNull(message = "The confirm Password cannot be null")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "The confirm password should be in a proper format")
    private String confirmPassword;

}
