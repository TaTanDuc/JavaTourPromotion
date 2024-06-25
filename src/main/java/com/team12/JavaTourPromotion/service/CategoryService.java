package com.team12.JavaTourPromotion.service;


import com.team12.JavaTourPromotion.model.Categories;
import com.team12.JavaTourPromotion.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
/**
 * Service class for managing categories.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;
    /**
     * Retrieve all categories from the database.
     * @return a list of categories
     */
    public List<Categories> getAllCategories(int x, int y, String s){
        return categoryRepository.findAll();
    }
    public Optional<Categories> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }
    public void addCategory(Categories category) {
        categoryRepository.save(category);
    }

    public void updateCategory(@NotNull Categories category) {
        Categories existingCategory = categoryRepository.findById((long)
                        category.getId())
                .orElseThrow(() -> new IllegalStateException("Category with ID " +
                        category.getId() + " does not exist."));
        existingCategory.setName(category.getName());
        existingCategory.setDestinations(category.getDestinations());
        categoryRepository.save(existingCategory);
    }
    public void deleteCategoryById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new IllegalStateException("Category with ID " + id + " does not exist.");
        }
        categoryRepository.deleteById(id);
    }
}