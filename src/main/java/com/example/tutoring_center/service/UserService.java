package com.example.tutoring_center.service;

import com.example.tutoring_center.dto.UserRequest;
import com.example.tutoring_center.dto.UserRequestUpdate;
import com.example.tutoring_center.dto.UserResponse;
import com.example.tutoring_center.entity.User;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);
    List<UserResponse> findAllUsers();
    UserResponse findUserById(Long id);
    UserResponse updateUser(Long id, UserRequestUpdate userRequestUpdate);
    void deleteUser(Long id);
}
