package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/category")
    public List<Category> getAllCategory(){
        return categoryService.findAllCategory();
    }

    @GetMapping("/category/list/{id}")
    public List<Category> getAllByCategoryTypes(@PathVariable(value = "id") long id){
        List<Category> categories = categoryRepository.findByCategoryTypes(id);
        return categories;
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Category category=categoryService.findCategoryById(id).orElseThrow(()-> new ResourceNotFoundException("Category not found"));
        return ResponseEntity.ok().body(category);
    }

    @PostMapping("/category")
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category){
        categoryService.save(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable(value = "id") long id, @Valid @RequestBody Category category) throws ResourceNotFoundException {
        Category currentCategory= categoryService.findCategoryById(id).orElseThrow(()-> new ResourceNotFoundException("Category not found"));

        currentCategory.setName(category.getName());
        currentCategory.setCategoryTypes(category.getCategoryTypes());
        categoryService.save(currentCategory);
        return ResponseEntity.ok(currentCategory);

    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Category category=categoryService.findCategoryById(id).orElseThrow(()-> new ResourceNotFoundException("Category not found"));
        categoryService.delete(category);
        return ResponseEntity.ok(category);
    }
}
