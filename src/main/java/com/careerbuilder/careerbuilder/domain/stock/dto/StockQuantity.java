package com.careerbuilder.careerbuilder.domain.stock.dto;

import com.careerbuilder.careerbuilder.domain.location.entity.Location;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockQuantity implements StockQuantityIfs{
    private Long locationId;
    private String locationName;
    private String locationMemo;
    private Integer totalQuantity;
}
