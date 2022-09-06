package com.demo.service;

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
        return setUser(user);
    }

    public List<UserDTO> getAll() {
        List<User> users = User.findAll().list();
        return setUsers(users);
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

    private UserDTO setUser(User user) {
        return UserDTO.builder()
                .id(user.id)
                .role(user.role)
                .name(user.name)
                .contactInfo(setContactInfo(user.contactInfo))
                .cards(setCards(user.cards))
                .build();
    }

    private List<UserDTO> setUsers(List<User> users) {
        return users.stream()
                .map(this::setUser)
                .collect(Collectors.toList());
    }

    private ContactInfoDTO setContactInfo(ContactInfo contactInfo) {
        return Objects.isNull(contactInfo) ? null : ContactInfoDTO.builder()
                .id(contactInfo.id)
                .email(contactInfo.email)
                .phone(contactInfo.phone)
                .telegram(contactInfo.telegram)
                .build();
    }

    private CardDTO setCard(Card card) {
        return Objects.isNull(card) ? null : CardDTO.builder()
                .id(card.id)
                .balance(card.balance)
                .currency(card.currency)
                .build();
    }

    private List<CardDTO> setCards(List<Card> cards) {
        return cards.stream()
                .map(this::setCard)
                .collect(Collectors.toList());
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
