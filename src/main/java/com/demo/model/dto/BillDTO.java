package com.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillDTO {
    private Long id;

    private String header;

    private Instant paidDate;

    private Float total;

    private CardDTO card;
}
