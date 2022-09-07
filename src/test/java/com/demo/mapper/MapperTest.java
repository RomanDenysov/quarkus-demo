package com.demo.mapper;

import com.demo.model.dto.CardDTO;
import com.demo.model.dto.UserDTO;
import com.demo.model.entity.Card;
import com.demo.model.entity.Currency;
import com.demo.model.entity.Role;
import com.demo.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MapperTest {

    @Test
    void testUserMapper() {
        UserDTO userDTO = UserDTO.builder()
                .id(1L)
                .name("Lion")
                .role(Role.ADMIN)
                .build();

        User user = UserMapper.INSTANCE.userDTOToUser(userDTO);

        Assertions.assertEquals(userDTO.getId(), user.id);
        Assertions.assertEquals(userDTO.getName(), user.name);
        Assertions.assertEquals(userDTO.getRole(), user.role);
        Assertions.assertNull(user.cards);
        Assertions.assertNull(user.contactInfo);

        Card card = new Card(1L, 200.0F, Currency.EUR, null, null);
        user.cards = List.of(card);

        UserDTO userDTO1 = UserMapper.INSTANCE.userToUserDTO(user);

        Assertions.assertEquals(user.id, userDTO1.getId());
        Assertions.assertEquals(user.name, userDTO1.getName());
        Assertions.assertEquals(user.role, userDTO1.getRole());
        Assertions.assertNotNull(userDTO1.getCards());
        Assertions.assertNull(userDTO1.getContactInfo());

        CardDTO cardDTO = userDTO1.getCards().get(0);

        Assertions.assertEquals(card.id, cardDTO.getId());
        Assertions.assertEquals(card.balance, cardDTO.getBalance());
        Assertions.assertEquals(card.currency, cardDTO.getCurrency());
        Assertions.assertNull(cardDTO.getBills());
    }
}
