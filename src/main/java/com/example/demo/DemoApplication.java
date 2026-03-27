package com.example.demo;

import com.example.demo.dao.ArticleRepository;
import com.example.demo.dao.CategoryRepository;
import com.example.demo.service.ArticleService;
import com.example.demo.service.CategoryService;
import com.example.demo.ui.ConsoleMenus;
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

  @Autowired
  private CategoryService categoryService;

  @Autowired
  private ArticleService articleService;


  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    var menus = new ConsoleMenus(categoryRepository,articleRepository,categoryService,articleService);
    menus.run();

    /* categoryRepository.save(new Category("Smartphone"));
    articleRepository.save(new Article("S9","Samsung",250));
    for (Article article : articleRepository.findByBrand("S9")) {
      System.out.println(article);
    }
    for (Article article : articleRepository.findByBrandAndPrice("Samsung",250)){
      System.out.println(article);
    }
    for (Article article : articleRepository.findByBrandAndPriceGreaterThan("Samsung",300)){
      System.out.println(article);
    }

    Category smartphone = categoryRepository.save(new Category("Smartphone"));
    Category tablet = categoryRepository.save(new Category("Tablet"));
    Category pc = categoryRepository.save(new Category("Pc"));

    articleRepository.save(new Article("S10", "Samsung", 500, smartphone));
    articleRepository.save(new Article("S9", "Samsung", 350, smartphone));
    articleRepository.save(new Article("MI10", "Xiaomi", 100, smartphone));

    articleRepository.save(new Article("GalaxyTab", "Samsung", 450, tablet));
    articleRepository.save(new Article("Ipad", "Apple", 350, tablet));

    articleRepository.save(new Article("R510", "Asus", 600, pc));
      for (Article article : articleRepository.findByCategoryId(4)) {
        System.out.println(article);
      }

    Optional<Article> articleById = articleRepository.findById(1L);
    System.out.println(articleById);


    //Exercice 1.2 : Trouver 2 moyens pour récupérer et afficher sur la console un article en base
    //puis, dans un second temps, afficher tous les articles en base.

     for (Article article : articleRepository.findAll()) {
        System.out.println(article);
    }

    //Exercice 1.3 : Ajouter une méthode qui permette de renvoyer tous les articles contenants
    //telle description et telle marque

    for (Article article : articleRepository.findByDescriptionAndBrand("Samsung","Samsung")) {
      System.out.println(article);
    }

    //Exercice 1.4 : Ajouter une méthode qui permet de supprimer un article à partir de l’id

    articleRepository.deleteById(1L);

    //Exercice 1.5 : Ajouter une méthode qui permet de mettre à jour un article à partir de l’id
    ArticleService articleService = new ArticleService(articleRepository);
    articleService.updateArticle(2L, "Samsung", "S10 Updated", 550);

    //Exercice 1.6 : Ajouter des moyens pour afficher les noms des catégories classés par ordre
    //croissant puis décroissant

    for (Category category : categoryRepository.findAll()){
      System.out.println(category);
    }

    //Exercice 1.7 : Ajouter une méthode de votre choix

    for (Article article : articleRepository.findAll()) {
      System.out.println(article);
    }*/


  }
}