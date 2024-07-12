package dev.patika.librarymanagementapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.patika.librarymanagementapi.entities.category.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}