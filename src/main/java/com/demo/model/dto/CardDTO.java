package com.demo.model.dto;

import com.demo.model.entity.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {
    private Long id;

    private Float balance;

    private Currency currency;

    private List<BillDTO> bills;
}
