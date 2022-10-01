package com.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO {
    private Long id;

    private String phone;

    private String email;

    private String telegram;

    private Long userId;
}
