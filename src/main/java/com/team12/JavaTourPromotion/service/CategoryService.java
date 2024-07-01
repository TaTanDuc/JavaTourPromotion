package com.team12.JavaTourPromotion.service;


import com.team12.JavaTourPromotion.model.Categories;
import com.team12.JavaTourPromotion.repository.CategoryRepository;
import com.team12.JavaTourPromotion.GetVM.CategoryGetVM;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryGetVM> getAllCategories(){
        return categoryRepository.findAll()
                .stream()
                .map(CategoryGetVM::from)
                .toList();
    }
    public Optional<Categories> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Categories addCategory(Categories category) {
        return categoryRepository.save(category);
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