package com.mycompany.property_management.service.impl;

import com.mycompany.property_management.dto.UserDTO;

public interface UserService {

    UserDTO register(UserDTO userDTO);
    UserDTO login(String email, String password);
}
