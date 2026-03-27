package com.example.demo.service;
import com.example.demo.dao.CategoryRepository;
import com.example.demo.entities.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
  private final CategoryRepository categoryRepository;
  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }
  public void updateCategory(Long id,String name ) {

    Category category = categoryRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Catégorie introuvable avec l'id: " + id));

    category.setName(name);

    categoryRepository.save(category);
  }
}