package com.demo.model.dto;

import com.demo.model.entity.Role;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "name"})
public class UserDTO {
    private Long id;

    private String name;

    private Role role;

    private List<CardDTO> cards;

    private ContactInfoDTO contactInfo;
}
