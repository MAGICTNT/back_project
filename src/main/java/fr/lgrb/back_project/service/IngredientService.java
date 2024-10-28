package fr.lgrb.back_project.service;

import fr.lgrb.back_project.entity.Ingredient;
import fr.lgrb.back_project.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient getIngredientById(Integer id) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        return ingredient.orElseThrow(() -> new RuntimeException("Ingredient not found with id " + id));
    }

    public Ingredient updateIngredient(Integer id, Ingredient ingredientDetails) {
        Ingredient existingIngredient = getIngredientById(id);
        existingIngredient.setTitle(ingredientDetails.getTitle());
        return ingredientRepository.save(existingIngredient);
    }

    public void deleteIngredient(Integer id) {
        ingredientRepository.deleteById(id);
    }

}
