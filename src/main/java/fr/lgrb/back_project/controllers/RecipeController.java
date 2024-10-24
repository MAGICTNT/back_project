package fr.lgrb.back_project.controllers;

import fr.lgrb.back_project.dto.RecipeDTO;
import fr.lgrb.back_project.entity.Recipe;

import fr.lgrb.back_project.repository.RecipeRepository;
import fr.lgrb.back_project.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/all")
    public List<RecipeDTO> getAllRecipes() {
        List<RecipeDTO> allRecipe = new ArrayList<>();
        List<Recipe> recipes = recipeRepository.findAll();
        for(Recipe recipe: recipes ){
            RecipeDTO newRecipeDto = new RecipeDTO();
            newRecipeDto.setDescription(recipe.getDescription());
            newRecipeDto.setCategoryTitle(recipe.getIdCategory() +""); // faire la récupération
            newRecipeDto.setPicture(recipe.getPicture());
            newRecipeDto.setDuration(recipe.getDuration());
            newRecipeDto.setSeen(recipe.getSeen());
            allRecipe.add(newRecipeDto);
        }
        return allRecipe;
    }


}
