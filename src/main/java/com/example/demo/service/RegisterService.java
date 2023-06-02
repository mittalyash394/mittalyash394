package com.example.demo.service;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.dto.UpdatePasswordDto;
import com.example.demo.entity.RegisterEntity;
import com.example.demo.exceptions.AlreadyPresentDetailException;
import com.example.demo.exceptions.UserNotFoundException;

import java.util.List;

public interface RegisterService {

    RegisterEntity registerUser(RegisterDto registerDto) throws AlreadyPresentDetailException;

    RegisterEntity getUserById(String userId) throws UserNotFoundException;

    List<RegisterEntity> allUsers();

    RegisterEntity loginUser(LoginDto loginDto) throws UserNotFoundException;

    Boolean deleteUserByUserId(String userId) throws UserNotFoundException;

    RegisterEntity updatePassword(String userId, UpdatePasswordDto updatePasswordDto) throws UserNotFoundException;
}
