package dev.patika.librarymanagementapi.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import dev.patika.librarymanagementapi.entities.category.Category;
import dev.patika.librarymanagementapi.entities.category.CategoryMapper;
import dev.patika.librarymanagementapi.entities.category.CategoryResponseDto;
import dev.patika.librarymanagementapi.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository.findAll()
                                 .stream()
                                 .map(CategoryMapper::categoryToCategoryResponseDto)
                                 .collect(Collectors.toList());
    }

    public CategoryResponseDto getCategoryById(int id) {
        Category category = categoryRepository.findById(id)
                                              .orElseThrow(() -> new EntityNotFoundException(
                                                      "Category not found with id: " + id));
        return CategoryMapper.categoryToCategoryResponseDto(category);
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(int id) {
        Category category = categoryRepository.findById(id)
                                              .orElseThrow(() -> new EntityNotFoundException(
                                                      "Category not found with id: " + id));

        if (!category.getBook()
                     .isEmpty())
        {
            throw new DataIntegrityViolationException(
                    "Cannot delete category with id " + id + " because it has associated books.");
        }

        categoryRepository.deleteById(id);
    }

}
