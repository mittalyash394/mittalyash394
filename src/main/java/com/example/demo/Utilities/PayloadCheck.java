package com.example.demo.Utilities;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.dto.UpdatePasswordDto;
import com.example.demo.dto.UserIdDto;
import com.example.demo.exceptions.LoginPayloadExceptions;
import com.example.demo.exceptions.NullUserIdExceptions;
import com.example.demo.exceptions.PasswordAndConfirmPasswordExceptions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Component
@Service
public class PayloadCheck {

    public boolean isValidPayload(UserIdDto userIdDto) {
        if (userIdDto.getUserId() == null || userIdDto.getUserId().isEmpty() || userIdDto.getUserId().equals(" ")) {
            throw new NullUserIdExceptions("UserId cannot be null or empty");
        }
        return true;

    }

    public boolean isLoginPayloadValid(LoginDto loginDto) {

        Pattern p = Pattern.compile("");
        String st = "";
        p.matcher(st).matches();
//            if(loginDto.getEmailId() == null){
//                throw new LoginPayloadExceptions("EmailId cannot be null");
//            }
        if (loginDto.getEmailId() == null || loginDto.getEmailId().isEmpty() || loginDto.getEmailId().equals(" ")) {
            throw new LoginPayloadExceptions("EmailId cannot be null or empty");
        }
        if (loginDto.getPassword() == null || loginDto.getPassword().isEmpty() || loginDto.getPassword().equals(" ")) {
            throw new LoginPayloadExceptions("Password cannot be null or empty");
        }
        return true;

    }

    public boolean isRegisterPayloadValid(RegisterDto registerDto) {

        if (registerDto.getEmailId() == null || registerDto.getEmailId().isEmpty() || registerDto.getEmailId().equals(" ")) {
            throw new LoginPayloadExceptions("EmailId cannot be null or empty");
        }
        if (registerDto.getPassword() == null || registerDto.getPassword().isEmpty() || registerDto.getPassword().equals(" ")) {
            throw new LoginPayloadExceptions("Password cannot be null or empty");
        }
        if (registerDto.getFirstName() == null || registerDto.getFirstName().isEmpty() || registerDto.getFirstName().equals(" ")) {
            throw new LoginPayloadExceptions("First name cannot be null or empty");
        }
        if (registerDto.getLastName() == null || registerDto.getLastName().isEmpty() || registerDto.getLastName().equals(" ")) {
            throw new LoginPayloadExceptions("Last name cannot be null or empty");
        }
        if (registerDto.getConfirmPassword() == null || registerDto.getConfirmPassword().isEmpty() || registerDto.getConfirmPassword().equals(" ")) {
            throw new LoginPayloadExceptions("Confirm password cannot be null or empty");
        }
        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            throw new PasswordAndConfirmPasswordExceptions("Password & Confirm password don't match");
        }
        return true;
    }

    public boolean isUpdatePasswordValid(UpdatePasswordDto updatePasswordDto) {

        if (updatePasswordDto.getUpdatePassword() == null || updatePasswordDto.getUpdatePassword().isEmpty() || updatePasswordDto.getUpdatePassword().equals(" ")) {
            throw new LoginPayloadExceptions("Password cannot be null or empty");
        }
        if (updatePasswordDto.getConfirmUpdatePassword() == null || updatePasswordDto.getConfirmUpdatePassword().isEmpty() || updatePasswordDto.getConfirmUpdatePassword().equals(" ")) {
            throw new LoginPayloadExceptions("Confirm password cannot be null or empty");
        }
        if (!(updatePasswordDto.getUpdatePassword()).equals(updatePasswordDto.getConfirmUpdatePassword())) {
            throw new PasswordAndConfirmPasswordExceptions("Password & Confirm password don't match");
        }
        return true;
    }

    public boolean isUserIdValid(String userId) {
        if (userId == null || userId.isEmpty() || userId.equals(" ")) {
            throw new NullUserIdExceptions("UserId cannot be null or empty");
        }
        return true;
    }
}
