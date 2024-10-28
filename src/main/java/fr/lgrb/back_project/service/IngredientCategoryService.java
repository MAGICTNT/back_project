package fr.lgrb.back_project.service;

import fr.lgrb.back_project.entity.IngredientCategory;
import fr.lgrb.back_project.repository.IngredientCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientCategoryService {
    private final IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    public IngredientCategoryService(IngredientCategoryRepository ingredientCategoryRepository) {
        this.ingredientCategoryRepository = ingredientCategoryRepository;
    }

    public IngredientCategory createIngredientCategory(IngredientCategory ingredientCategory) {
        return ingredientCategoryRepository.save(ingredientCategory);
    }

    public List<IngredientCategory> getAllIngredientCategorys() {
        return ingredientCategoryRepository.findAll();
    }

    public IngredientCategory getIngredientCategoryById(Integer id) {
        Optional<IngredientCategory> ingredientCategory = ingredientCategoryRepository.findById(id);
        return ingredientCategory.orElseThrow(() -> new RuntimeException("IngredientCategory not found with id " + id));
    }

    public IngredientCategory updateIngredientCategory(Integer id, IngredientCategory ingredientCategoryDetails) {
        IngredientCategory existingIngredientCategory = getIngredientCategoryById(id);
        existingIngredientCategory.setTitle(ingredientCategoryDetails.getTitle());
        return ingredientCategoryRepository.save(existingIngredientCategory);
    }

    public void deleteIngredientCategory(Integer id) {
        ingredientCategoryRepository.deleteById(id);
    }
}
