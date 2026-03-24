package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Category implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  @OneToMany(mappedBy = "category")
  private Collection<Article> articles;

  public Category() {}

  public Category(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Category(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Category{" +
        "id=" + id +
        ", name='" + name +  '}';
  }
}
