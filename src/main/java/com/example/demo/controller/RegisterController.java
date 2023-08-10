package com.example.demo.controller;

import com.example.demo.Utilities.PayloadCheck;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.dto.UserIdDto;
import com.example.demo.dto.UpdatePasswordDto;
import com.example.demo.entity.RegisterEntity;
import com.example.demo.exceptions.*;
import com.example.demo.service.RegisterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/registration")
@Validated
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @Autowired(required = true)
    private PayloadCheck payloadCheck;

    @Autowired
    private ExceptionHandlerController exceptionHandlerController;

    public static final Logger log = LogManager.getLogger(RegisterController.class);

    public static String PASSWORD_NOT_MATCH = "PASSWORD AND CONFIRM_PASSWORD NOT MATCHED";
    public static String ALREADY_PRESENT_EMAIL_ID = "EMAIL_ID IS ALREADY PRESENT IN THE DB";

    public static String USER_NOT_FOUND = "USER ID NOT FOUND";

    public static String USER_ID_CANNOT_BE_NULL = "USER ID CANNOT BE NULL/EMPTY";

    public static String EMPTY_USER_ID = "USER ID CANNOT BE EMPTY";

    public static String USER_ID_PAYLOAD = "USER_ID PAYLOAD IS NOT CORRECT";

    public static String LOGIN_PAYLOAD = "THE LOGIN PAYLOAD IS NOT CORRECT";

    @Value("${spring.application.name}")
    String applicationName;

    @CrossOrigin
    @GetMapping(value = "/root", headers = "Accept=application/json")
    public String home() {
        log.info("The application name is " + applicationName);
        log.info("This is home page");
        return "This is ";
    }

    @PostMapping(value = "/registerUser", headers = "Accept=application/json")
    public RegisterEntity registerUser(@Valid @NotNull @RequestBody RegisterDto registerDto) throws AlreadyPresentDetailException, PasswordAndConfirmPasswordExceptions, RegisterUserPayloadExceptions {
        RegisterEntity registerEntity = null;
        log.info("Checking if the payload for registration of a user is correct or not");
        if (!payloadCheck.isRegisterPayloadValid(registerDto)) {
            throw new RegisterUserPayloadExceptions(USER_ID_PAYLOAD);
        }
        try {
            log.info("Registration of user");
            registerEntity = registerService.registerUser(registerDto);
        } catch (AlreadyPresentDetailException alreadyPresentDetailException) {
            log.error(alreadyPresentDetailException.toString());
            throw new AlreadyPresentDetailException(ALREADY_PRESENT_EMAIL_ID);
        }
        log.info(registerEntity);
        return registerEntity;
    }

    @CrossOrigin
    @PostMapping(value = "/login", headers = "Accept=application/json")
    public RegisterEntity loginUser(@Valid @RequestBody LoginDto loginDto) throws UserNotFoundException {
        log.info("Logging in for the user");
        RegisterEntity registerEntity = null;
        log.info("The login payload is " + loginDto);
        if (!payloadCheck.isLoginPayloadValid(loginDto)) {
            throw new LoginPayloadExceptions(LOGIN_PAYLOAD);
        }
        try {
            log.info("In loop of logging in");
            registerEntity = registerService.loginUser(loginDto);
        } catch (UserNotFoundException userNotFoundException) {
            log.error(userNotFoundException.toString());
            throw new UserNotFoundException(USER_NOT_FOUND);
        }
        return registerEntity;
    }

    @CrossOrigin
    @GetMapping(value = "/getUserById/{userId}", headers = "Accept=application/json")
    public RegisterEntity getUserById(@PathVariable String userId) throws UserNotFoundException {
        RegisterEntity registerEntity = null;
        log.info("Getting the user info");
        if (!payloadCheck.isUserIdValid(userId)) {
            throw new NullEmptyUserIdExceptions(USER_ID_CANNOT_BE_NULL);
        }
        try {
            log.info("In loop of getting the user info");
            registerEntity = registerService.getUserById(userId);
        } catch (UserNotFoundException userNotFoundException) {
            log.error(userNotFoundException.toString());
            throw new UserNotFoundException(USER_NOT_FOUND);
        }
        return registerEntity;
    }

    @CrossOrigin
    @GetMapping(value = "/allUsers", headers = "Accept=application/json")
    public List<RegisterEntity> allUsers() {
        log.info("In loop of getting all the users");
        List<RegisterEntity> allUsers = registerService.allUsers();
        log.info(allUsers);
        return allUsers;
    }

    @CrossOrigin
    @DeleteMapping(value = "deleteUserByUserId", headers = "Accept=application/json")
    public Boolean deleteUserByUserId(@RequestBody UserIdDto userIdDto) throws UserIdPayloadExceptions {
        boolean isDeleted = false;
        try {
            if (!payloadCheck.isValidPayload(userIdDto)) {
                throw new UserIdPayloadExceptions(USER_ID_PAYLOAD);
            }
        } catch (UserIdPayloadExceptions ex) {
            log.error(ex.toString());
            throw new UserIdPayloadExceptions(USER_ID_PAYLOAD);
        }
        try {
            log.error("The userId is " + userIdDto.getUserId());
            log.info("The type of userId is " + userIdDto.getUserId().getClass().getTypeName());
            log.info("Deletion of the user");
            log.info("In loop of the deletion of the user");
            isDeleted = registerService.deleteUserByUserId(userIdDto.getUserId());
            isDeleted = true;
        } catch (UserNotFoundException userNotFoundException) {
            log.error(userNotFoundException.toString());
            throw new UserNotFoundException(USER_NOT_FOUND);
        }
        return isDeleted;
    }

    @CrossOrigin
    @PutMapping(value = "updatePassword/{userId}", headers = "Accept=application/json")
    public RegisterEntity updatePassword(@Valid @PathVariable String userId, @Valid @RequestBody UpdatePasswordDto updatePasswordDto) throws UserNotFoundException, UpdatePasswordPayloadExceptions {
        RegisterEntity registerEntity = null;
        log.info("Checking for updatePasswordPayload");
        if (!payloadCheck.isUserIdValid(userId)) {
            throw new NullEmptyUserIdExceptions(USER_ID_CANNOT_BE_NULL);
        }
        if (!payloadCheck.isUpdatePasswordValid(updatePasswordDto)) {
            throw new UpdatePasswordPayloadExceptions(PASSWORD_NOT_MATCH);
        }
        try {
            log.info("In loop of the updating the password of the user");
            registerEntity = registerService.updatePassword(userId, updatePasswordDto);
        } catch (UserNotFoundException userNotFoundException) {
            log.error(userNotFoundException.toString());
            throw new UserNotFoundException(USER_NOT_FOUND);
        }
        return registerEntity;
    }


}
