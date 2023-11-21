package com.anand.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anand.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
