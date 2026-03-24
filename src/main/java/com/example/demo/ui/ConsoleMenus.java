package com.example.demo.ui;

import com.example.demo.dao.ArticleRepository;
import com.example.demo.dao.CategoryRepository;
import com.example.demo.entities.Article;
import com.example.demo.entities.Category;
import com.example.demo.service.ArticleService;
import java.util.Scanner;

public class ConsoleMenus {

    private final Scanner in = new Scanner(System.in);
    private static final String CHOICE_TEXT = "Choose: ";
    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;

  public ConsoleMenus(CategoryRepository categoryRepository, ArticleRepository articleRepository) {
    this.articleRepository = articleRepository;
    this.categoryRepository = categoryRepository;
  }
    public void run() {
      mainMenu();   // blocks until user exits
      System.out.println("Goodbye.");
    }
    private void mainMenu() {
      while (true) {
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("1) Afficher tous les articles sans pagination");
        System.out.println("2) Afficher tous les articles avec pagination");
        System.out.println("3) Ajouter un article");
        System.out.println("4) Afficher un article");
        System.out.println("5) Supprimer un article");
        System.out.println("6) Modifier un article");
        System.out.println("7) Ajouter une catégorie");
        System.out.println("8) Afficher une catégorie");
        System.out.println("9) Supprimer une catégorie");
        System.out.println("10) Mettre à jour une catégorie");
        System.out.println("11) Afficher tous les articles d'une catégorie");
        System.out.println("0) Sortir du programme");

        int choice = readInt(CHOICE_TEXT, 0, 12);
        switch (choice) {
          case 1 -> displayArticlesPagingMenu();
          case 2 -> displayArticlesNoPagingMenu();
          case 3 -> addArticleMenu();
          case 4 -> displayArticleMenu();
          case 5 -> deleteArticleMenu();
          case 6 -> modifyArticleMenu();
          case 7 -> addCategoryMenu();
          case 8 -> displayCategoryMenu();
          case 9 -> deleteCategoryMenu();
          case 10 -> modifyCategoryMenu();
          case 11 -> displayAllArticlesFromCategoryMenu();
          case 0 -> { return; } // exit application
          default -> {return;}
        }
      }
    }

  private void displayArticlesPagingMenu() {
  }

  private void displayArticlesNoPagingMenu() {
    for (Article article : articleRepository.findAll()) {
      System.out.println(article);
    }
  }


  private void addArticleMenu() {
    System.out.println("Entrer la marque ");
    String brand = in.nextLine().trim();
    System.out.println("Entrer une description");
    String description = in.nextLine().trim();
    System.out.println("Renseigner le prix");
    double price = in.nextInt();
    System.out.println("Choisissez la catégorie");
    for (Category category : categoryRepository.findAll()){
      System.out.println(category.getId() + ")  " + category.getName());
    }

    Object[] result = categoryRepository.findMinAndMaxId();
    Object[] row = (Object[]) result[0];

    long minId = ((Number) row[0]).longValue();
    long maxId = ((Number) row[1]).longValue();

    int min = (int) minId;
    int max = (int) maxId;
    long categoryId = readInt(CHOICE_TEXT, min, max);
    Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new IllegalArgumentException("Catégorie introuvable"));

    articleRepository.save(new Article(description, brand, price, category));
  }

  private void displayArticleMenu() {

  }

  private void deleteArticleMenu() {
    System.out.println("Entrer l'id de l'article à supprimer ");
    for (Article article : articleRepository.findAll()) {
      System.out.println(article);
    }
    Object[] result = articleRepository.findMinAndMaxId();
    Object[] row = (Object[]) result[0];

    long minId = ((Number) row[0]).longValue();
    long maxId = ((Number) row[1]).longValue();

    int min = (int) minId;
    int max = (int) maxId;

    long articleId = readInt(CHOICE_TEXT, min, max);
    articleRepository.deleteById(articleId);
    System.out.println("L'article a bien été supprimé ");
  }

  private void modifyArticleMenu() {
    System.out.println("Entrer l'id de l'article à modifier ");
    for (Article article : articleRepository.findAll()) {
      System.out.println(article);
    }

    Object[] result = articleRepository.findMinAndMaxId();
    Object[] row = (Object[]) result[0];

    long minId = ((Number) row[0]).longValue();
    long maxId = ((Number) row[1]).longValue();

    int min = (int) minId;
    int max = (int) maxId;

    long articleId = readInt(CHOICE_TEXT, min, max);
    ArticleService articleService = new ArticleService(articleRepository);

    System.out.println("Entrer la marque ");
    String brand = in.nextLine().trim();
    System.out.println("Entrer une description");
    String description = in.nextLine().trim();
    System.out.println("Renseigner le prix");
    double price = in.nextInt();
    System.out.println("Choisissez la catégorie");
    for (Category category : categoryRepository.findAll()){
      System.out.println(category.getId() + ")  " + category.getName());
    }

    Object[] resultCat = categoryRepository.findMinAndMaxId();
    Object[] rowCat = (Object[]) resultCat[0];

    long minIdCat = ((Number) rowCat[0]).longValue();
    long maxIdCat = ((Number) rowCat[1]).longValue();

    int intMinIdCat = (int) minIdCat;
    int intMaxIdCat = (int) maxIdCat;

    long categoryId = readInt(CHOICE_TEXT, intMinIdCat, intMaxIdCat);
    Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new IllegalArgumentException("Catégorie introuvable"));

    articleService.updateArticle(articleId, brand, description, price,category);

  }

  private void addCategoryMenu() {
    System.out.println("Entrer le nom de la nouvelle catégorie ");
    String categoryName = in.nextLine().trim();
    categoryRepository.save(new Category(categoryName));
    System.out.println("La catégorie a bien été ajoutée");
  }

  private void displayCategoryMenu() {
  }

  private void deleteCategoryMenu() {

  }

  private void modifyCategoryMenu() {
  }

  private void displayAllArticlesFromCategoryMenu() {
  }

  private int readInt(String prompt, int min, int max) {
    while (true) {
      System.out.print(prompt);
      String s = in.nextLine().trim();
      try {
        int v = Integer.parseInt(s);
        if (v < min || v > max) {
          System.out.printf("Enter %d..%d%n", min, max);
          continue;
        }
        return v;
      } catch (NumberFormatException e) {
        System.out.println("Invalid number.");
      }
    }
  }


}

