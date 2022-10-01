package com.demo.model.mapper;

import com.demo.model.dto.CardDTO;
import com.demo.model.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CardMapper {
    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

    CardDTO cardToCardDTO(Card card);

    Card cardDTOToCard(CardDTO cardDTO);
}
