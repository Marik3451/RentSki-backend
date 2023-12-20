package com.rentsky.service.impl;

import com.rentsky.dto.GetCategoryDTO;
import com.rentsky.entity.Category;
import com.rentsky.entity.DayValue;
import com.rentsky.repository.CategoryRepository;
import com.rentsky.repository.DayValueRepository;
import com.rentsky.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final DayValueRepository dayValueRepository;
    @Override
    public List<GetCategoryDTO> getCategories(Integer page, Integer pageSize) {
        return categoryRepository.findAll(PageRequest.of(page, pageSize)).get().map(this::mapEntityToGetDTO).toList();
    }
    private GetCategoryDTO mapEntityToGetDTO(Category category){
        List<DayValue> prices = dayValueRepository.getAllByCategory_Id(category.getId());
        return GetCategoryDTO.builder()
                .id(category.getId())
                .category(category.getCategory())
                .prices(prices)
                .build();
    }
}
