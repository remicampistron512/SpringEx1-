package com.example.demo.service;

import com.example.demo.dao.ArticleRepository;
import com.example.demo.entities.Article;
import com.example.demo.entities.Category;
import java.util.Optional;

public class ArticleService {
  private final ArticleRepository articleRepository;
  public ArticleService(ArticleRepository articleRepository) {
    this.articleRepository = articleRepository;
  }
  public Article updateArticle(Long id, String brand, String description, double price, Category category ) {

    Article article = articleRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Article not found with id: " + id));

    article.setBrand(brand);
    article.setDescription(description);
    article.setPrice(price);
    article.setCategory(category);

    return articleRepository.save(article);
  }
}