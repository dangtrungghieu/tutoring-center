package com.example.tutoring_center.service.impl;

import com.example.tutoring_center.dto.UserRequest;
import com.example.tutoring_center.dto.UserRequestUpdate;
import com.example.tutoring_center.dto.UserResponse;
import com.example.tutoring_center.entity.User;
import com.example.tutoring_center.repository.UserRepository;
import com.example.tutoring_center.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private UserResponse mapToUserResponse(User user) {
        return new UserResponse(user.getId(), user.getEmail(), user.getRole());
    }
    @Override
    public UserResponse createUser(UserRequest userRequest){
        if (userRepository.existsByEmail(userRequest.email())){
            throw new RuntimeException("Email đã được sử dụng!");
        }
        User user = new User();
        user.setEmail(userRequest.email());
        user.setPassword(userRequest.password());
        user.setRole(userRequest.role());
        return mapToUserResponse(userRepository.save(user));
    }
    @Override
    public List<UserResponse> findAllUsers(){
        return userRepository.findAll().stream().map(this::mapToUserResponse).toList();
    }
    @Override
    public UserResponse findUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy user có ID là: " + id));
        return mapToUserResponse(user);
    }
    @Override
    @Transactional
    public UserResponse updateUser(Long id, UserRequestUpdate userRequestUpdate){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy user có ID là: " + id));
        user.setPassword(userRequestUpdate.password());
        user.setRole(userRequestUpdate.role());
        return mapToUserResponse(userRepository.save(user));
    }
    @Override
    @Transactional
    public void deleteUser(Long id){
        if (!userRepository.existsById(id)){
            throw new RuntimeException("Không tìm thấy user có ID là: " + id);
        }
        userRepository.deleteById(id);
    }
}
