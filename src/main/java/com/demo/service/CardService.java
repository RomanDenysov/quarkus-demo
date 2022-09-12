package com.demo.service;

import com.demo.model.dto.CardDTO;

import java.util.List;

public interface CardService {
    List<CardDTO> getAllUsersCards(Long userId);

    CardDTO getCardByUserIdAndCardId(Long userId, Long cardId);

    void createCardByUserId(Long userId, CardDTO card);

    void updateCardByUserId(Long userId, Long cardId, CardDTO card);

    void deleteCardByUserId(Long userId, Long cardId);
}
