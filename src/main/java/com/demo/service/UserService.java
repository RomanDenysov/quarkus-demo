package com.demo.service;

import com.demo.mapper.UserMapper;
import com.demo.model.dto.CardDTO;
import com.demo.model.dto.ContactInfoDTO;
import com.demo.model.dto.UserDTO;
import com.demo.model.entity.Card;
import com.demo.model.entity.ContactInfo;
import com.demo.model.entity.User;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {

    public UserDTO getById(Long id) {
        User user = (User) User.findByIdOptional(id).orElseThrow(() -> new WebApplicationException(404));
        return UserMapper.INSTANCE.userToUserDTO(user);
    }

    public List<UserDTO> getAll() {
        List<User> users = User.findAll().list();
        return users.stream()
                .map(UserMapper.INSTANCE::userToUserDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void create(UserDTO user) {
        isNull(user);
        isAlreadyExist(user);
        new User(user.getName(), user.getRole()).persist();
    }

    @Transactional
    public void update(Long id, UserDTO userDTO) {
        isNull(userDTO);
        isAlreadyExist(userDTO);

        User user = (User) User.findByIdOptional(id).orElseThrow(() -> new WebApplicationException(404));

        user.name = Objects.isNull(userDTO.getName()) ? user.name : userDTO.getName();
        user.role = Objects.isNull(userDTO.getRole()) ? user.role : userDTO.getRole();
    }

    @Transactional
    public void updateContactInfo() {
        throw new UnsupportedOperationException();
    }

    @Transactional
    public void updateCards() {
        throw new UnsupportedOperationException();
    }

    @Transactional
    public void delete(Long id) {
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
