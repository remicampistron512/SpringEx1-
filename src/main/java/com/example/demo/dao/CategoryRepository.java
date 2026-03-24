package com.example.demo.dao;

import com.example.demo.entities.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category,Long> {
  public List<Category> findAll();
  @Query("SELECT MIN(c.id), MAX(c.id) FROM Category c")
  Object[] findMinAndMaxId();
}
