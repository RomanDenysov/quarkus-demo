package com.demo.service.impl;

import com.demo.mapper.UserMapper;
import com.demo.model.dto.ContactDTO;
import com.demo.model.dto.UserDTO;
import com.demo.model.entity.User;
import com.demo.service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Override
    public UserDTO getById(Long id) {
        User user = (User) User.findByIdOptional(id).orElseThrow(() -> new WebApplicationException(404));
        return UserMapper.INSTANCE.userToUserDTO(user);
    }

    @Override
    public UserDTO getByName(String name) {
        User user = User.findUserByName(name)
                .orElseThrow(() -> new WebApplicationException(404));
        return UserMapper.INSTANCE.userToUserDTO(user);
    }

    @Override
    public List<UserDTO> getAll() {
        List<User> users = User.findAll().list();
        return users.stream()
                .map(UserMapper.INSTANCE::userToUserDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void createUser(UserDTO user) {
        isNull(user);
        isAlreadyExist(user);
        new User(user.getName(), user.getRole()).persist();
    }

    @Transactional
    @Override
    public void updateUser(Long id, UserDTO userDTO) {
        isNull(userDTO);
        isAlreadyExist(userDTO);

        User user = (User) User.findByIdOptional(id).orElseThrow(() -> new WebApplicationException(404));

        user.name = Objects.isNull(userDTO.getName()) ? user.name : userDTO.getName();
        user.role = Objects.isNull(userDTO.getRole()) ? user.role : userDTO.getRole();
    }

    @Transactional
    @Override
    public void updateContact(UserDTO userDTO, ContactDTO contactDTO) {
        isNull(userDTO);

        User user = (User) User.findByIdOptional(userDTO.getId()).orElseThrow(() -> new WebApplicationException(404));

        user.contactId = contactDTO.getId();
    }

    @Transactional
    public void updateCards() {
        throw new UnsupportedOperationException();
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        User user = (User) User.findByIdOptional(id).orElseThrow(() -> new WebApplicationException(404));
        user.delete();
    }

    public List<UserDTO> getUsersRestrict() {
        return User.findUserRestrictInfo();
    }

    private void isNull(UserDTO user) {
        if(Objects.isNull(user)) {
            throw new WebApplicationException(400);
        }
    }

    /**
     * throws exception if user already exist
     * */
    private void isAlreadyExist(UserDTO user) {
        if(User.findUserByName(user.getName()).isPresent()) {
            throw new WebApplicationException(400);
        }
    }
}
