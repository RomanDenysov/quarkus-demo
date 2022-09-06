package com.demo.service;

import com.demo.model.dto.BillDTO;
import com.demo.model.dto.CardDTO;
import com.demo.model.dto.UserDTO;
import com.demo.model.entity.Bill;
import com.demo.model.entity.Card;
import com.demo.model.entity.User;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class CardService {

    @Transactional
    public void create(CardDTO cardDTO) {

    }

    public CardDTO getById(Long id) {
        Card card = (Card) Card.findByIdOptional(id).orElseThrow(() -> new WebApplicationException(404));
        return setCard(card);
    }

    @Transactional
    public void update(Long id, CardDTO cardDTO) {

    }

    @Transactional
    public void delete(Long id) {

    }

    public List<CardDTO> getAll() {
        List<Card> cards = Card.findAll().list();
        return setCards(cards);
    }

    public List<CardDTO> getAllUsersCards(Long userId) {
        User user = (User) User.findByIdOptional(userId).orElseThrow(() -> new WebApplicationException(404));
        return user.cards.stream()
                .map(this::setCard)
                .collect(Collectors.toList());
    }

    public CardDTO getCardByUserIdAndCardId(Long userId, Long cardId) {
        return getAllUsersCards(userId).stream()
                .filter(c -> c.getId().equals(cardId))
                .findFirst()
                .orElseThrow(() -> new WebApplicationException(404));
    }

    public void createCardByUserId(Long userId, CardDTO card) {

    }

    public void updateCardByUserId(Long userId, Long cardId, CardDTO card) {

    }

    public void deleteCardByUserId(Long userId, Long cardId) {

    }

    private CardDTO setCard(Card card) {
        return Objects.isNull(card) ? null : CardDTO.builder()
                .id(card.id)
                .balance(card.balance)
                .currency(card.currency)
                .users(setUsers(card.users))
                .bills(setBills(card.bills))
                .build();
    }

    private List<CardDTO> setCards(List<Card> cards) {
        return cards.stream()
                .map(this::setCard)
                .collect(Collectors.toList());
    }

    private List<BillDTO> setBills(List<Bill> bills) {
        return bills.stream()
                .map(this::setBill)
                .collect(Collectors.toList());
    }

    private List<UserDTO> setUsers(List<User> users) {
        return users.stream()
                .map(this::setUser)
                .collect(Collectors.toList());
    }

    private BillDTO setBill(Bill bill) {
        return BillDTO.builder()
                .header(bill.header)
                .total(bill.total)
                .paidDate(bill.paidDate)
                .build();
    }

    private UserDTO setUser(User user) {
        return Objects.isNull(user) ? null : UserDTO.builder()
                .id(user.id)
                .name(user.name)
                .role(user.role)
                .build();
    }
}
