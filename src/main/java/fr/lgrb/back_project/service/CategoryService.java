package fr.lgrb.back_project.service;

import fr.lgrb.back_project.entity.Category;
import fr.lgrb.back_project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategorys() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElseThrow(() -> new RuntimeException("Category not found with id " + id));
    }

    public Category updateCategory(Integer id, Category categoryDetails) {
        Category existingCategory = getCategoryById(id);
        existingCategory.setTitle(categoryDetails.getTitle());
        return categoryRepository.save(existingCategory);
    }

    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }
}
