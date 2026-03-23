package com.example.demo;

import com.example.demo.dao.ArticleRepository;
import com.example.demo.dao.CategoryRepository;
import com.example.demo.entities.Article;
import com.example.demo.entities.Category;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication(scanBasePackages = "com.example")
public class DemoApplication implements CommandLineRunner {

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private ArticleRepository articleRepository;

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    //categoryRepository.save(new Category("Smartphone"));
    //articleRepository.save(new Article("S9","Samsung",250));
//    for (Article article : articleRepository.findByBrand("S9")) {
//      System.out.println(article);
//    }
//    for (Article article : articleRepository.findByBrandAndPrice("Samsung",250)){
//      System.out.println(article);
//    }
//    for (Article article : articleRepository.findByBrandAndPriceGreaterThan("Samsung",300)){
//      System.out.println(article);
//    }

//    Category smartphone = categoryRepository.save(new Category("Smartphone"));
//    Category tablet = categoryRepository.save(new Category("Tablet"));
//    Category pc = categoryRepository.save(new Category("Pc"));
//
//    articleRepository.save(new Article("S10", "Samsung", 500, smartphone));
//    articleRepository.save(new Article("S9", "Samsung", 350, smartphone));
//    articleRepository.save(new Article("MI10", "Xiaomi", 100, smartphone));
//
//    articleRepository.save(new Article("GalaxyTab", "Samsung", 450, tablet));
//    articleRepository.save(new Article("Ipad", "Apple", 350, tablet));
//
//    articleRepository.save(new Article("R510", "Asus", 600, pc));
      for (Article article : articleRepository.findByCategoryId(4)) {
        System.out.println(article);
      }

//
//    }

  }
}