package com.example.demo.Utilities;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.dto.UpdatePasswordDto;
import com.example.demo.dto.UserIdDto;
import com.example.demo.exceptions.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Service
public class PayloadCheck {

    public boolean isValidPayload(UserIdDto userIdDto) {
        if (userIdDto.getUserId() == null || userIdDto.getUserId().isEmpty() || userIdDto.getUserId().equals(" ")) {
            throw new NullEmptyUserIdExceptions("UserId cannot be null or empty");
        }
        return true;

    }

    public boolean isLoginPayloadValid(LoginDto loginDto) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        String passwordRegex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
        if (loginDto.getEmailId() == null || loginDto.getEmailId().isEmpty() || loginDto.getEmailId().equals(" ")) {
            throw new LoginPayloadExceptions("EmailId cannot be null or empty");
        }
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailIdMatcher = emailPattern.matcher(loginDto.getEmailId());
        if(!(emailIdMatcher.matches())){
            throw new EmailIdFormatExceptions("EmailId is not in valid format");
        }
        Pattern passwordPattern = Pattern.compile(passwordRegex);
        Matcher passwordMatcher = passwordPattern.matcher(loginDto.getPassword());
        if(!(passwordMatcher.matches())){
            throw new PasswordFormatExceptions("Password is not in valid format");
        }
        if (loginDto.getPassword() == null || loginDto.getPassword().isEmpty() || loginDto.getPassword().equals(" ")) {
            throw new LoginPayloadExceptions("Password cannot be null or empty");
        }
        return true;
    }

    public boolean isRegisterPayloadValid(RegisterDto registerDto) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        String passwordRegex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        String nameRegex= "[A-Z][a-z]*";
        if (registerDto.getEmailId() == null || registerDto.getEmailId().isEmpty() || registerDto.getEmailId().equals(" ")) {
            throw new LoginPayloadExceptions("EmailId cannot be null or empty");
        }
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailIdMatcher = emailPattern.matcher(registerDto.getEmailId());
        if(!(emailIdMatcher.matches())){
            throw new EmailIdFormatExceptions("EmailId is not in valid format");
        }
        if (registerDto.getPassword() == null || registerDto.getPassword().isEmpty() || registerDto.getPassword().equals(" ")) {
            throw new LoginPayloadExceptions("Password cannot be null or empty");
        }
        Pattern passwordPattern = Pattern.compile(passwordRegex);
        Matcher passwordMatcher = passwordPattern.matcher(registerDto.getPassword());
        if(!(passwordMatcher.matches())){
            throw new PasswordFormatExceptions("Password is not in valid format");
        }
        if (registerDto.getFirstName() == null || registerDto.getFirstName().isEmpty() || registerDto.getFirstName().equals(" ")) {
            throw new LoginPayloadExceptions("First name cannot be null or empty");
        }
        Pattern namePattern = Pattern.compile(nameRegex);
        Matcher firstNameMatcher = namePattern.matcher(registerDto.getFirstName());
        if(!(firstNameMatcher.matches())){
            throw new FirstNameFormatExceptions("First name is not in valid format");
        }
        if (registerDto.getLastName() == null || registerDto.getLastName().isEmpty() || registerDto.getLastName().equals(" ")) {
            throw new LoginPayloadExceptions("Last name cannot be null or empty");
        }
        Matcher lastNameMatcher = namePattern.matcher(registerDto.getLastName());
        if(!(lastNameMatcher.matches())){
            throw new LastNameFormatExceptions("Last name is not in valid format");
        }
        if (registerDto.getConfirmPassword() == null || registerDto.getConfirmPassword().isEmpty() || registerDto.getConfirmPassword().equals(" ")) {
            throw new LoginPayloadExceptions("Confirm password cannot be null or empty");
        }
        Matcher confirmPasswordMatcher = passwordPattern.matcher(registerDto.getConfirmPassword());
        if(!(confirmPasswordMatcher.matches())){
            throw new ConfirmPasswordFormatExceptions("Confirm password is not in valid format");
        }
        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            throw new PasswordAndConfirmPasswordExceptions("Password & Confirm password don't match");
        }
        return true;
    }

    public boolean isUpdatePasswordValid(UpdatePasswordDto updatePasswordDto) {

        String passwordRegex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        if (updatePasswordDto.getUpdatePassword() == null || updatePasswordDto.getUpdatePassword().isEmpty() || updatePasswordDto.getUpdatePassword().equals(" ")) {
            throw new LoginPayloadExceptions("Password cannot be null or empty");
        }
        Pattern passwordPattern = Pattern.compile(passwordRegex);
        Matcher passwordMatcher = passwordPattern.matcher(updatePasswordDto.getUpdatePassword());
        if(!(passwordMatcher.matches())){
            throw new PasswordFormatExceptions("Password is not in valid format");
        }
        if (updatePasswordDto.getConfirmUpdatePassword() == null || updatePasswordDto.getConfirmUpdatePassword().isEmpty() || updatePasswordDto.getConfirmUpdatePassword().equals(" ")) {
            throw new LoginPayloadExceptions("Confirm password cannot be null or empty");
        }
        Matcher confirmPasswordMatcher = passwordPattern.matcher(updatePasswordDto.getConfirmUpdatePassword());
        if(!(confirmPasswordMatcher.matches())){
            throw new ConfirmPasswordFormatExceptions("Confirm password is not in valid format");
        }
        if (!(updatePasswordDto.getUpdatePassword()).equals(updatePasswordDto.getConfirmUpdatePassword())) {
            throw new PasswordAndConfirmPasswordExceptions("Password & Confirm password don't match");
        }
        return true;
    }

    public boolean isUserIdValid(String userId) {
        if (userId == null || userId.isEmpty() || userId.equals(" ")) {
            throw new NullEmptyUserIdExceptions("UserId cannot be null or empty");
        }
        return true;
    }
}
