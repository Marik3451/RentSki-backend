package com.rentsky.service;

import com.rentsky.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories(Integer page, Integer pageSize);
}
