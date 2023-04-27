package com.example.classactivity.service;

import com.example.classactivity.dto.UserDTO;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    public UserDTO loginUser(UserDTO userDTO);
    public UserDTO updateUser(UserDTO userDTO);
    void deleteUSer();

}
