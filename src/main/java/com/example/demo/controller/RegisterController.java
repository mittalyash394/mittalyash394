package com.example.demo.controller;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.dto.UpdatePasswordDto;
import com.example.demo.entity.RegisterEntity;
import com.example.demo.exceptions.AlreadyPresentDetailException;
import com.example.demo.exceptions.PasswordAndConfirmPasswordExceptions;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.service.RegisterService;
import com.example.demo.service.RegisterServiceImpl;
import org.apache.catalina.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/registration")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    public static final Logger log = LogManager.getLogger(RegisterController.class);

    public static String PASSWORD_NOT_MATCH = "PASSWORD AND CONFIRM_PASSWORD NOT MATCH";
    public static String ALREADY_PRESENT_EMAIL_ID = "EMAIL_ID IS ALREADY PRESENT IN THE DB";

    public static String USER_NOT_FOUND = "USER ID NOT FOUND";

    @Value("${spring.application.name}")
    String applicationName;

    @CrossOrigin
    @GetMapping(value = "/root", headers = "Accept=application/json")
    public String home(){

        log.info("The application name is " + applicationName);

        log.info("This is home page");
        return "This is ";
    }

    @PostMapping(value = "/registerUser", headers = "Accept=application/json")
    public RegisterEntity registerUser(@Valid @NotNull @RequestBody RegisterDto registerDto) throws AlreadyPresentDetailException, PasswordAndConfirmPasswordExceptions {
        RegisterEntity registerEntity = null;

        log.info("Checking if the password and the confirmPassword are same");
        log.info("In loop for checking if the password and confirmPassword are same");
        if(registerDto.getPassword().equals(registerDto.getConfirmPassword())){
            log.info("The passwords are same");
            try{
                log.info("Registration of user");
                registerEntity = registerService.registerUser(registerDto);
            }catch (AlreadyPresentDetailException alreadyPresentDetailException){
                log.error(alreadyPresentDetailException.toString());
                throw new AlreadyPresentDetailException(ALREADY_PRESENT_EMAIL_ID);
            }
            log.info(registerEntity);
        }
        else {
            log.error("The passwords don't match");
            throw new PasswordAndConfirmPasswordExceptions(PASSWORD_NOT_MATCH);
        }
        return registerEntity;
    }

    @CrossOrigin
    @PostMapping(value = "/login", headers = "Accept=application/json")
    public RegisterEntity loginUser(@Valid @RequestBody LoginDto loginDto) throws UserNotFoundException {
        log.info("Logging in for the user");
        RegisterEntity registerEntity = null;
        try{
            log.info("In loop of logging in");
             registerEntity = registerService.loginUser(loginDto);
        }catch (UserNotFoundException userNotFoundException){
            log.error(userNotFoundException.toString());
            throw new UserNotFoundException(USER_NOT_FOUND);
        }
        return registerEntity;
    }

    @CrossOrigin
    @GetMapping(value = "/getUserById/{userId}", headers = "Accept=application/json")
    public RegisterEntity getUserById(@Valid @Pattern(regexp = "(?i)^(?=.*[a-z])[a-z0-9]{24,30}$", message = "The userId should be in proper format") @NotNull @NotEmpty @Positive @PathVariable String userId) throws UserNotFoundException {

        RegisterEntity registerEntity = null;
        log.info("Getting the user info");
        try {
            log.info("In loop of getting the user info");
            registerEntity = registerService.getUserById(userId);
        }catch(UserNotFoundException userNotFoundException){
            log.error(userNotFoundException.toString());
            throw new UserNotFoundException(USER_NOT_FOUND);
        }
        return registerEntity;
    }

    @CrossOrigin
    @GetMapping(value = "/allUsers", headers = "Accept=application/json")
    public List<RegisterEntity> allUsers(){
        log.info("In loop of getting all the users");
        List<RegisterEntity> allUsers = registerService.allUsers();
        log.info(allUsers);
        return allUsers;
    }

    @CrossOrigin
    @DeleteMapping(value = "deleteUserByUserId/{userId}", headers = "Accept=application/json")
    public Boolean deleteUserByUserId(@Valid @Pattern(regexp = "(?i)^(?=.*[a-z])[a-z0-9]{8,20}$", message = "The userId should be in a proper format") @NotNull @NotEmpty @Positive @PathVariable String userId){
        boolean isDeleted = false;
        log.info("Deletion of the user");
        try{
            log.info("In loop oogf the deletion of the user");
            isDeleted = registerService.deleteUserByUserId(userId);
            isDeleted = true;
        }catch (UserNotFoundException userNotFoundException){
            log.error(userNotFoundException.toString());
        }
        return isDeleted;
    }


    @CrossOrigin
    @PutMapping(value = "updatePassword/{userId}", headers = "Accept=application/json")
    public RegisterEntity updatePassword(@Valid @Pattern(regexp = "(?i)^(?=.*[a-z])[a-z0-9]{8,20}$", message = "The userId should be in a proper format") @NotNull @NotEmpty @Positive @PathVariable String userId,@Valid @RequestBody UpdatePasswordDto updatePasswordDto) throws UserNotFoundException, PasswordAndConfirmPasswordExceptions {
        RegisterEntity registerEntity = null;
        log.info("Updating the password of the user");
        if(updatePasswordDto.getUpdatePassword().equals(updatePasswordDto.getConfirmUpdatePassword())){
            log.info("The passwords are same, updating them");
            try{
                log.info("In loop of the updating the password of the user");
                registerEntity = registerService.updatePassword(userId, updatePasswordDto);
            }catch (UserNotFoundException userNotFoundException){
                log.error(userNotFoundException.toString());
                throw new UserNotFoundException(USER_NOT_FOUND);
            }
        }
        else {
            log.error("The passwords don't match");
            log.info("Hello Yash");
            throw new PasswordAndConfirmPasswordExceptions(PASSWORD_NOT_MATCH);
        }
        return registerEntity;
    }


}
