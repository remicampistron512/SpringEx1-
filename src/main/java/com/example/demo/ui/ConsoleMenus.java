package com.example.demo.ui;

import com.example.demo.dao.ArticleRepository;
import com.example.demo.dao.CategoryRepository;
import com.example.demo.entities.Article;
import com.example.demo.entities.Category;
import com.example.demo.service.ArticleService;
import com.example.demo.service.CategoryService;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * Classe controlant l'interface console du programme
 */
public class ConsoleMenus {

  private static final String CHOICE_TEXT = "Votre choix : ";
  private final Scanner in = new Scanner(System.in);
  private final ArticleRepository articleRepository;
  private final CategoryRepository categoryRepository;
  private final ArticleService articleService;
  private final CategoryService categoryService;

  /**
   * On injecte les services et repository nécessaires
   */
  public ConsoleMenus(CategoryRepository categoryRepository,
      ArticleRepository articleRepository,
      CategoryService categoryService,
      ArticleService articleService) {
    this.articleRepository = articleRepository;
    this.categoryRepository = categoryRepository;
    this.categoryService = categoryService;
    this.articleService = articleService;
  }

  public void run() {
    mainMenu();
    System.out.println("A bientôt.");
  }

  /**
   * Affiche le menu principal
   */
  private void mainMenu() {
    while (true) {
      System.out.println("\n=== MENU PRINCIPAL ===");
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

      int choice = readInt(CHOICE_TEXT, 0, 11);
      switch (choice) {
        case 1 -> displayArticlesNoPagingMenu();
        case 2 -> displayArticlesPagingMenu(5);
        case 3 -> addArticleMenu();
        case 4 -> displayArticleMenu();
        case 5 -> deleteArticleMenu();
        case 6 -> modifyArticleMenu();
        case 7 -> addCategoryMenu();
        case 8 -> displayCategoryMenu();
        case 9 -> deleteCategoryMenu();
        case 10 -> modifyCategoryMenu();
        case 11 -> displayAllArticlesFromCategoryMenu();
        case 0 -> {
          return;
        } // exit application
        default -> System.out.println("Choix invalide.");
      }
    }
  }

  /**
   * Affiche les articles avec pagination
   * @param pageSize le nombre de lignes à afficher
   */
  private void displayArticlesPagingMenu(int pageSize) {
    int pageNumber = 0;
    boolean running = true;

    while (running) {
      Pageable pageable = PageRequest.of(pageNumber, pageSize);
      Page<Article> page = articleRepository.findAll(pageable);

      System.out.println();
      System.out.println("=== ARTICLES ===");

      if (page.isEmpty()) {
        System.out.println("Aucun article trouvé.");
        return;
      }

      System.out.println("Page " + (pageNumber + 1) + " / " + page.getTotalPages());
      System.out.println("Nombre total d'articles : " + page.getTotalElements());
      System.out.println();

      for (Article article : page.getContent()) {
        System.out.println(article);
      }

      System.out.println();
      System.out.println("[N] page suivante");
      System.out.println("[P] page précédente");
      System.out.println("[PAGE] pour définir le nombre de lignes par page");
      System.out.println("[Q] quitter");

      String choice = in.nextLine().trim().toUpperCase();

      switch (choice) {
        case "N":
          if (page.hasNext()) {
            pageNumber++;
          } else {
            System.out.println("Vous êtes déjà sur la dernière page.");
          }
          break;

        case "P":
          if (page.hasPrevious()) {
            pageNumber--;
          } else {
            System.out.println("Vous êtes déjà sur la première page.");
          }
          break;

        case "Q":
          running = false;
          break;

        case "PAGE":
          pageSize = readInt("Combien de lignes par page ? ", 1, 100);
          pageNumber = 0;
          break;

        default:
          System.out.println("Choix invalide.");
      }
    }

  }

  /**
   * Affiche les articles sans pagination
   */
  private void displayArticlesNoPagingMenu() {
    printAllArticles();
  }

  /**
   * Ajoute un article
   */
  private void addArticleMenu() {
    System.out.println("Entrer la marque ");
    String brand = in.nextLine().trim();

    System.out.println("Entrer une description");
    String description = in.nextLine().trim();

    System.out.println("Renseigner le prix");
    double price = readDouble(CHOICE_TEXT);

    System.out.println("Choisissez la catégorie");
    for (Category category : categoryRepository.findAll()) {
      System.out.println(category.getId() + ")  " + category.getName());
    }

    long categoryId = readExistingCategoryId();

    Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new IllegalArgumentException("Catégorie introuvable"));

    articleRepository.save(new Article(description, brand, price, category));
  }

  /**
   * Affiche les details d'un article
   */
  private void displayArticleMenu() {
    System.out.println("Entrer l'id de l'article à afficher ");
    printAllArticles();
    long articleId = readExistingArticleId();
    Optional<Article> article = articleRepository.findById(articleId);
    System.out.println(article);


  }

  /**
   * Supprime un article
   */
  private void deleteArticleMenu() {
    System.out.println("Entrer l'id de l'article à supprimer ");
    printAllArticles();

    long articleId = readExistingArticleId();

    try {
      articleRepository.deleteById(articleId);
      System.out.println("L'article a bien été supprimé.");
    } catch (Exception e) {
      System.out.println("Impossible de supprimer l'article : " + e.getMessage());
    }

  }

  /**
   * Modifie un article
   */
  private void modifyArticleMenu() {
    System.out.println("Entrer l'id de l'article à modifier ");
    printAllArticles();

    long articleId = readExistingArticleId();

    System.out.println("Entrer la marque ");
    String brand = in.nextLine().trim();
    System.out.println("Entrer une description");
    String description = in.nextLine().trim();
    System.out.println("Renseigner le prix");
    double price = readDouble(CHOICE_TEXT);
    System.out.println("Choisissez la catégorie");
    for (Category category : categoryRepository.findAll()) {
      System.out.println(category.getId() + ")  " + category.getName());
    }

    long categoryId = readExistingCategoryId();
    Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new RuntimeException("Catégorie introuvable"));

    articleService.updateArticle(articleId, brand, description, price, category);

  }

  /**
   * Ajoute une catégorie
   */
  private void addCategoryMenu() {
    System.out.println("Entrer le nom de la nouvelle catégorie ");
    String categoryName = in.nextLine().trim();
    categoryRepository.save(new Category(categoryName));
    System.out.println("La catégorie a bien été ajoutée");
  }

  private void displayCategoryMenu() {
       // Aucune utilité pour l'instant
  }

  private void deleteCategoryMenu() {
    System.out.println("Entrer l'id de la catégorie à supprimer ");
    printAllCategories();
    long categoryId = readExistingCategoryId();
    try {
      categoryRepository.deleteById(categoryId);
      System.out.println("La catégorie a bien été supprimée.");
    } catch (Exception e) {
      System.out.println("Impossible de supprimer la catégorie : " + e.getMessage());
    }

  }

  /**
   * Modifie une catégorie
   */
  private void modifyCategoryMenu() {

    printAllCategories();

    long categoryId = readExistingCategoryId();

    System.out.println("Entrer le nom de la catégorie ");
    String categoryName = in.nextLine().trim();

    categoryService.updateCategory(categoryId, categoryName);


  }

  /**
   * Affiche les articles appartenant à une catégorie
   */
  private void displayAllArticlesFromCategoryMenu() {
    System.out.println("Entrer l'id de la catégorie à afficher ");
    printAllCategories();
    int categoryId = (int) readExistingCategoryId();
    for (Article article : articleRepository.findByCategoryId(categoryId)) {
      System.out.println(article);
    }
  }

  /**
   * Permet de saisir un entier et de vérifier sa validité
   * @param prompt Message à afficher
   * @param min Valeur minimum
   * @param max Valeur maximum
   * @return la valeur nettoyée
   */
  private int readInt(String prompt, int min, int max) {
    while (true) {
      System.out.print(prompt);
      String s = in.nextLine().trim();
      try {
        int v = Integer.parseInt(s);
        if (v < min || v > max) {
          System.out.printf("Saisir %d..%d%n", min, max);
          continue;
        }
        return v;
      } catch (NumberFormatException e) {
        System.out.println("Nombre invalide.");
      }
    }
  }

  /**
   * Permet de saisir un double et de vérifier sa validité
   * @param prompt Message à afficher
   * @return  la valeur nettoyée
   */
  private double readDouble(String prompt) {
    while (true) {
      System.out.print(prompt);
      String s = in.nextLine().trim();
      try {
        return Double.parseDouble(s);
      } catch (NumberFormatException e) {
        System.out.println("Nombre invalide.");
      }
    }
  }

  private long readLong(String prompt) {
    while (true) {
      System.out.print(prompt);
      String input = in.nextLine();

      try {
        return Long.parseLong(input.trim());
      } catch (NumberFormatException e) {
        System.out.println("Veuillez entrer un nombre valide.");
      }
    }
  }

  /**
   * Permet de saisir une id de catégorie et de voir si elle existe
   * @return l'id de catégorie valide
   */
  private long readExistingCategoryId() {
    while (true) {
      long id = readLong("Choisissez l'id de la catégorie : ");
      if (categoryRepository.existsById(id)) {
        return id;
      }
      System.out.println("Catégorie introuvable.");
    }
  }

  /**
   * Permet de saisir un id d'article et de vérifier si l'article existe
   * @return l'id d'un article valide
   */
  private long readExistingArticleId() {
    while (true) {
      long id = readLong("Choisissez l'id de l'article : ");
      if (articleRepository.existsById(id)) {
        return id;
      }
      System.out.println("Article introuvable.");
    }
  }

  /**
   * Affiche tous les articles
   */
  private void printAllArticles() {
    for (Article article : articleRepository.findAll()) {
      System.out.println(article);
    }
  }

  /**
   * Affiche toutes les catégories
   */
  private void printAllCategories() {
    for (Category category : categoryRepository.findAll()) {
      System.out.println(category);
    }
  }


}


