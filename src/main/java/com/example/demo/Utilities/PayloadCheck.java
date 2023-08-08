package com.example.demo.Utilities;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.UserIdDto;
import com.example.demo.exceptions.LoginPayloadExceptions;
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
}
