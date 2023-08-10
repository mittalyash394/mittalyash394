package com.example.demo.service;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.dto.UpdatePasswordDto;
import com.example.demo.entity.RegisterEntity;
import com.example.demo.exceptions.AlreadyPresentDetailException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.repo.RegisterRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterRepo registerRepo;

    public static final Logger log = LogManager.getLogger(RegisterServiceImpl.class);

    public static String ALREADY_PRESENT_EMAIL_ID = "EMAIL_ID IS ALREADY PRESENT IN THE DB";

    public static String USER_NOT_FOUND = "USER ID NOT FOUND";

    @Override
    public RegisterEntity registerUser(RegisterDto registerDto) throws AlreadyPresentDetailException {
        RegisterEntity registerEntity = new RegisterEntity();
        Date date = new Date();
        RegisterEntity registerEntityFromDB = registerRepo.findByEmailId(registerDto.getEmailId());
        log.info(registerEntityFromDB);
        if (registerEntityFromDB != null) {
            log.info(ALREADY_PRESENT_EMAIL_ID);
            throw new AlreadyPresentDetailException(ALREADY_PRESENT_EMAIL_ID);
        } else {
            registerEntity.setFirstName(registerDto.getFirstName());
            registerEntity.setLastName(registerDto.getLastName());
            registerEntity.setEmailId(registerDto.getEmailId());
            registerEntity.setPassword(registerDto.getPassword());
            registerEntity.setConfirmPassword(registerDto.getConfirmPassword());
            registerEntity.setCreatedAt(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(date));
            registerRepo.save(registerEntity);
            log.info(registerDto);
        }
        return registerEntity;
    }

    @Override
    public RegisterEntity loginUser(LoginDto loginDto) throws UserNotFoundException {
        RegisterEntity registerEntity = registerRepo.findUserByEmailIdAndPassword(loginDto.getEmailId(), loginDto.getPassword());
        log.info(registerEntity);
        if (registerEntity == null) {
            throw new UserNotFoundException(USER_NOT_FOUND);
        }
        return registerEntity;
    }

    @Override
    public RegisterEntity getUserById(String userId) throws UserNotFoundException {
        Optional<RegisterEntity> registerEntity = registerRepo.findById(userId);
        log.info("Getting the user details by userId");
        if (registerEntity.isEmpty()) {
            throw new UserNotFoundException(USER_NOT_FOUND);
        }
        return registerEntity.get();
    }

    @Override
    public List<RegisterEntity> allUsers() {
        log.info("Getting all the users");
        List<RegisterEntity> allUsers = registerRepo.findAll();
        if (allUsers.size() == 0) {
            return allUsers;
        }
        return allUsers;
    }

    @Override
    public Boolean deleteUserByUserId(String userId) throws UserNotFoundException {
        Optional<RegisterEntity> registerEntity = registerRepo.findById(userId);
        log.info("Deletion of the user by the userId");
        if (registerEntity.isEmpty()) {
            throw new UserNotFoundException(USER_NOT_FOUND);
        }
        RegisterEntity isDeleted = registerRepo.deleteUserByUserId(userId);
        log.info(isDeleted);
        return true;
    }

    @Override
    public RegisterEntity updatePassword(String userId, UpdatePasswordDto updatePasswordDto) throws UserNotFoundException {
        RegisterEntity registerEntity = new RegisterEntity();
        Date date = new Date();
        Optional<RegisterEntity> registerEntityFromDB = registerRepo.findById(userId);
        log.info("Updating the password of the user");
        if (registerEntityFromDB.isEmpty()) {
            throw new UserNotFoundException(USER_NOT_FOUND);
        } else {
            log.info("In loop of updating the user's password");
            registerEntity.setUserId(userId);
            registerEntity.setFirstName(registerEntityFromDB.get().getFirstName());
            registerEntity.setLastName(registerEntityFromDB.get().getLastName());
            registerEntity.setEmailId(registerEntityFromDB.get().getEmailId());
            registerEntity.setCreatedAt(registerEntityFromDB.get().getCreatedAt());
            registerEntity.setPassword(updatePasswordDto.getUpdatePassword());
            registerEntity.setConfirmPassword(updatePasswordDto.getConfirmUpdatePassword());
            registerEntity.setUpdatedAt(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(date));
            registerRepo.save(registerEntity);
        }
        return registerEntity;
    }

}
