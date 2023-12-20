package com.rentsky.service;

import com.rentsky.dto.GetCategoryDTO;

import java.util.List;

public interface CategoryService {
    List<GetCategoryDTO> getCategories(Integer page, Integer pageSize);
}
