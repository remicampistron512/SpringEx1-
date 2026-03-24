package com.example.demo.service;

import com.example.demo.dao.ArticleRepository;
import com.example.demo.dao.CategoryRepository;
import com.example.demo.entities.Article;
import com.example.demo.entities.Category;

public class CategoryService {
  private final CategoryRepository categoryRepository;
  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }
  public Category updateCategory(Long id,String name ) {

    Category category = categoryRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Category  not found with id: " + id));

    category.setName(name);


    return categoryRepository.save(category);
  }
}