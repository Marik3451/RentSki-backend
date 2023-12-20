package com.rentsky.service.impl;

import com.rentsky.entity.Category;
import com.rentsky.repository.CategoryRepository;
import com.rentsky.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public List<Category> getCategories(Integer page, Integer pageSize) {
        return categoryRepository.findAll(PageRequest.of(page, pageSize)).get().toList();
    }
}
