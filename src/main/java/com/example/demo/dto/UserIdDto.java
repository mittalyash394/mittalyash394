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
public class UserIdDto {

//    @Pattern(regexp = "(?i)^(?=.*[a-z])[a-z0-9]{8,20}$", message = "The userId should be in a proper format")
//    @NotNull(message = "The user Id can n ot b e nu ll")
//    @NotEmpty(message = "The userId can not b e EMPTY")
    private String userId;

}
