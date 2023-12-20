package com.rentsky.dto;

import com.rentsky.entity.DayValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCategoryDTO {
    private UUID id;
    private String category;
    private List<DayValue> prices;
}
