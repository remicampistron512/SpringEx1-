package com.example.demo.ui;

import com.example.demo.dao.ArticleRepository;
import com.example.demo.dao.CategoryRepository;
import com.example.demo.entities.Article;
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
  }

  private void displayArticleMenu() {
  }

  private void deleteArticleMenu() {
  }

  private void modifyArticleMenu() {
  }

  private void addCategoryMenu() {
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

