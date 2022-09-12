package com.demo.service;

import com.demo.model.dto.ContactDTO;
import com.demo.model.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO getById(Long id);

    UserDTO getByName(String name);

    List<UserDTO> getAll();

    void createUser(UserDTO user);

    void updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);

    void updateContact(UserDTO userDTO, ContactDTO contactDTO);
}
