package com.example.demo.Utilities;

import com.example.demo.dto.UserIdDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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

}
