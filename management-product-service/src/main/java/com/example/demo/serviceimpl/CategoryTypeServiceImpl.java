package com.example.demo.serviceimpl;

import com.example.demo.model.CategoryType;
import com.example.demo.repository.CategoryTypeRepository;
import com.example.demo.service.CategoryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryTypeServiceImpl implements CategoryTypeService {

    CategoryTypeRepository categoryTypeRepository;

    @Autowired
    public CategoryTypeServiceImpl (CategoryTypeRepository categoryTypeRepository){
        this.categoryTypeRepository=categoryTypeRepository;
    }

    @Override
    public List<CategoryType> findAllCategoryTypes() {
        return categoryTypeRepository.findAll();
    }

    @Override
    public Optional<CategoryType> findCategoryTypesById(long id) {
        return categoryTypeRepository.findById(id);
    }

    @Override
    public void save(CategoryType categoryType) {
        categoryTypeRepository.save(categoryType);

    }

    @Override
    public void delete(CategoryType categoryType) {
        categoryTypeRepository.delete(categoryType);
    }
}
