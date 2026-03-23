package com.example.demo.dao;

import com.example.demo.entities.Article;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  ArticleRepository extends JpaRepository<Article,Long> {
  public List<Article> findByBrand(String brand);
  public List<Article> findByBrandContains(String brand);
  public List<Article> findByBrandAndPrice(String brand, double price);
  public List<Article> findByBrandAndPriceGreaterThan(String brand,double price);
  public List<Article> findByCategoryId(Integer categoryId);
  //Exercice 1.3
  public List<Article> findByDescriptionAndBrand(String description, String brand);
}
