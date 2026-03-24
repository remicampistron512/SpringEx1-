package com.example.demo.dao;

import com.example.demo.entities.Article;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface  ArticleRepository extends JpaRepository<Article,Long> {
  public List<Article> findByBrand(String brand);
  public List<Article> findByBrandContains(String brand);
  public List<Article> findByBrandAndPrice(String brand, double price);
  public List<Article> findByBrandAndPriceGreaterThan(String brand,double price);
  public List<Article> findByCategoryId(Integer categoryId);
  //Exercice 1.3
  public List<Article> findByDescriptionAndBrand(String description, String brand);
  //Exercice 1.4
  public void deleteById(Long articleId);

  @Query("SELECT MIN(a.id), MAX(a.id) FROM Article a")
  Object[] findMinAndMaxId();
}
