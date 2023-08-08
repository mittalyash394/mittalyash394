package com.example.demo.Utilities;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.dto.UpdatePasswordDto;
import com.example.demo.dto.UserIdDto;
import com.example.demo.exceptions.LoginPayloadExceptions;
import com.example.demo.exceptions.PasswordAndConfirmPasswordExceptions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Component
@Service
public class PayloadCheck {

    public boolean isValidPayload(UserIdDto userIdDto){
        try {
            if(userIdDto.getUserId() == null){
                return false;
            }
            if(userIdDto.getUserId().isEmpty()){
                return false;
            }
            return true;
        }catch (Exception e){
            throw e;
//            return false;
        }
    }

    public boolean isLoginPayloadValid(LoginDto loginDto) {

        Pattern p = Pattern.compile("");
        String st = "";
        p.matcher(st).matches();
//            if(loginDto.getEmailId() == null){
//                throw new LoginPayloadExceptions("EmailId cannot be null");
//            }
            if(loginDto.getEmailId() == null || loginDto.getEmailId().isEmpty()){
                throw new LoginPayloadExceptions("EmailId cannot be null or empty");
            }
            if(loginDto.getPassword() == null || loginDto.getPassword().isEmpty()){
                throw new LoginPayloadExceptions("Password cannot be null or empty");
            }
            return true;

    }

    public boolean isRegisterPayloadValid(RegisterDto registerDto) {

        if(registerDto.getEmailId() == null || registerDto.getEmailId().isEmpty()){
            throw new LoginPayloadExceptions("EmailId cannot be null or empty");
        }
        if(registerDto.getPassword() == null || registerDto.getPassword().isEmpty()){
            throw new LoginPayloadExceptions("Password cannot be null or empty");
        }
        if(registerDto.getFirstName() == null || registerDto.getFirstName().isEmpty()){
            throw new LoginPayloadExceptions("First name cannot be null or empty");
        }
        if(registerDto.getLastName() == null || registerDto.getLastName().isEmpty()){
            throw new LoginPayloadExceptions("Last name cannot be null or empty");
        }
        if(registerDto.getConfirmPassword() == null || registerDto.getConfirmPassword().isEmpty()){
            throw new LoginPayloadExceptions("Confirm password cannot be null or empty");
        }
        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())){
            throw new PasswordAndConfirmPasswordExceptions("Password & Confirm password don't match");
        }
        return true;
    }

    public boolean isUpdatePasswordValid(UpdatePasswordDto updatePasswordDto) {

        if(updatePasswordDto.getUpdatePassword() == null || updatePasswordDto.getUpdatePassword().isEmpty()){
            throw new LoginPayloadExceptions("Password cannot be null or empty");
        }
        if(updatePasswordDto.getConfirmUpdatePassword() == null || updatePasswordDto.getConfirmUpdatePassword().isEmpty()){
            throw new LoginPayloadExceptions("Confirm password cannot be null or empty");
        }
        if (!(updatePasswordDto.getUpdatePassword()).equals(updatePasswordDto.getConfirmUpdatePassword())){
            throw new PasswordAndConfirmPasswordExceptions("Password & Confirm password don't match");
        }
        return true;
    }
}
