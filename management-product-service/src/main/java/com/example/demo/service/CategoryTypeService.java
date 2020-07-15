package com.example.demo.service;


import com.example.demo.model.CategoryType;

import java.util.List;
import java.util.Optional;

public interface CategoryTypeService {
    List<CategoryType> findAllCategoryTypes();
    Optional<CategoryType> findCategoryTypesById(long id);
    void save(CategoryType categoryType);
    void delete(CategoryType categoryType);
}
