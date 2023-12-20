package com.rentsky.controller;


import com.rentsky.entity.Category;
import com.rentsky.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<Category> getCategory(@RequestParam(required = false, defaultValue = "0")
                             Integer page,
                                      @RequestParam(required = false, defaultValue = "50")
                             Integer pageSize) {
        return categoryService.getCategories(page, pageSize);
    }
}
