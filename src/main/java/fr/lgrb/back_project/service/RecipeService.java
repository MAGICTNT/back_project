package fr.lgrb.back_project.service;

import fr.lgrb.back_project.entity.Recipe;
import fr.lgrb.back_project.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe getRecipeById(Integer id) {
        System.out.println("service " + id);
        Optional<Recipe> recipe = recipeRepository.findById(id);
        return recipe.orElseThrow(() -> new RuntimeException("Recipe not found with id " + id));
    }

    public Recipe updateRecipe(Integer id, Recipe recipeDetails) {
        Recipe existingRecipe = getRecipeById(id);
        existingRecipe.setTitle(recipeDetails.getTitle());
        existingRecipe.setNumberPeople(recipeDetails.getNumberPeople());
        existingRecipe.setDuration(recipeDetails.getDuration());
        existingRecipe.setDescription(recipeDetails.getDescription());
        existingRecipe.setSeen(recipeDetails.getSeen());
        existingRecipe.setPicture(recipeDetails.getPicture());
        existingRecipe.setIdNutrition(recipeDetails.getIdNutrition());
        existingRecipe.setIdCategory(recipeDetails.getIdCategory());
        return recipeRepository.save(existingRecipe);
    }

    public void deleteRecipe(Integer id) {
        recipeRepository.deleteById(id);
    }

    public Recipe getRecipeByIdName(String title){
        return recipeRepository.getRecipesByTitle(title);
    }

}
