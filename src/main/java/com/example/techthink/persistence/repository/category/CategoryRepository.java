package com.example.techthink.persistence.repository.category;

import com.example.techthink.persistence.entity.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
