package com.personal.beshophihi.service;

import com.personal.beshophihi.dto.UserDTO;
import com.personal.beshophihi.model.User;

import java.util.List;

public interface UserService {
    User addNewUser(UserDTO userDTO);
    List<User> getUsers();
    User getUserById(Long id);
}
