package com.example.demo.service;

import com.example.demo.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAllCategory();
    Optional<Category> findCategoryById(long id);
    void save(Category category);
    void delete(Category category);
}
