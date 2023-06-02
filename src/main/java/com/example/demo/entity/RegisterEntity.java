package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class RegisterEntity {

    @Id
    private String userId;

    @NotEmpty(message = "The first name cannot be empty")
    @NotNull(message = "The first name cannot be null")
    private String firstName;

    @NotEmpty(message = "The last name cannot be empty")
    @NotNull(message = "The last name cannot be null")
    private String lastName;

    @NotEmpty(message = "The emailId cannot be empty")
    @NotNull(message = "The emailId cannot be null")
    @Email(message = "The email should be valid")
    private String emailId;

    @NotEmpty(message = "The password cannot be empty")
    @NotNull(message = "The password cannot be null")
    private String password;

    @NotEmpty(message = "The confirm Password cannot be empty")
    @NotNull(message = "The confirm Password cannot be null")
    private String confirmPassword;

    private String createdAt;

    private String updatedAt;
}
