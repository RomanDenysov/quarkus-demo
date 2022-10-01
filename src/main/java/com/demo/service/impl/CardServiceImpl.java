package com.demo.service.impl;

import com.demo.model.mapper.CardMapper;
import com.demo.model.dto.CardDTO;
import com.demo.model.entity.Card;
import com.demo.model.entity.User;
import com.demo.service.CardService;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CardServiceImpl implements CardService {

    @Override
    public List<CardDTO> getAllUsersCards(Long userId) {
        User user = (User) User.findByIdOptional(userId).orElseThrow(() -> new WebApplicationException(404));
        return user.cards.stream()
                .map(CardMapper.INSTANCE::cardToCardDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CardDTO getCardByUserIdAndCardId(Long userId, Long cardId) {
        return getAllUsersCards(userId).stream()
                .filter(c -> c.getId().equals(cardId))
                .findFirst()
                .orElseThrow(() -> new WebApplicationException(404));
    }

    @Override
    public void createCardByUserId(Long userId, CardDTO card) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateCardByUserId(Long userId, Long cardId, CardDTO card) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteCardByUserId(Long userId, Long cardId) {
        throw new UnsupportedOperationException();
    }

    @Transactional
    public void createCard(CardDTO cardDTO) {
        throw new UnsupportedOperationException();
    }

    public CardDTO getById(Long id) {
        Card card = (Card) Card.findByIdOptional(id).orElseThrow(() -> new WebApplicationException(404));
        return CardMapper.INSTANCE.cardToCardDTO(card);
    }

    @Transactional
    public void update(Long id, CardDTO cardDTO) {
        throw new UnsupportedOperationException();
    }

    @Transactional
    public void delete(Long id) {
        throw new UnsupportedOperationException();
    }

    public List<CardDTO> getAll() {
        List<Card> cards = Card.findAll().list();
        return cards.stream()
                .map(CardMapper.INSTANCE::cardToCardDTO)
                .collect(Collectors.toList());
    }
}
