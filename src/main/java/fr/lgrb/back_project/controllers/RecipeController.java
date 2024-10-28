package fr.lgrb.back_project.controllers;

import fr.lgrb.back_project.dto.*;
import fr.lgrb.back_project.entity.Constituted;
import fr.lgrb.back_project.entity.ConstitutedId;
import fr.lgrb.back_project.entity.Ingredient;
import fr.lgrb.back_project.entity.Recipe;

import fr.lgrb.back_project.error.ResourceNotFoundException;
import fr.lgrb.back_project.repository.CategoryRepository;
import fr.lgrb.back_project.repository.NutritionRepository;
import fr.lgrb.back_project.repository.RecipeRepository;
import fr.lgrb.back_project.service.ConstitutedService;
import fr.lgrb.back_project.service.IngredientService;
import fr.lgrb.back_project.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;
    private NutritionRepository nutritionRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    private RecipeService recipeService;
    @Autowired
    private ConstitutedService constitutedService;
    @Autowired
    private IngredientService ingredientService;


//    @GetMapping("/all2")
//    public List<RecipeDTO> getAllRecipes() {
//        List<RecipeDTO> allRecipe = new ArrayList<>();
//        List<Recipe> recipes = recipeRepository.findAll();
//        for(Recipe recipe: recipes ){
//            RecipeDTO newRecipeDto = new RecipeDTO();
//            newRecipeDto.setId(recipe.getId());
//            newRecipeDto.setTitle(recipe.getTitle());
//            newRecipeDto.setNumberPeople(recipe.getNumberPeople());
//            newRecipeDto.setDescription(recipe.getDescription());
//            newRecipeDto.setCategoryTitle(recipe.getIdCategory().getTitle()); // faire la récupération
//            newRecipeDto.setNutritionTitle(recipe.getIdNutrition().getTitle());
//            newRecipeDto.setPicture(recipe.getPicture());
//            newRecipeDto.setDuration(recipe.getDuration());
//            newRecipeDto.setSeen(recipe.getSeen());
//            allRecipe.add(newRecipeDto);
//        }
//        return allRecipe;
//    }

    @GetMapping("/all")
    public List<ConstitutedDTO> getFullRecipes() {
        List<ConstitutedDTO> allConstituted = new ArrayList<>();
        Map<Integer, ConstitutedDTO> constitutedMap = new HashMap<>();

        for (Constituted constituted : constitutedService.findAll()) {
            Recipe recipe = constituted.getIdRecipe();
            Integer recipeId = recipe.getId();

            ConstitutedDTO constitutedDTO;

            // Si la recette existe déjà dans le map, récupérez-la
            if (constitutedMap.containsKey(recipeId)) {
                constitutedDTO = constitutedMap.get(recipeId);
            } else {
                // Sinon, créez une nouvelle entrée pour la recette
                RecipeDTO recipeDTO = new RecipeDTO();
                recipeDTO.setId(recipeId);
                recipeDTO.setTitle(recipe.getTitle());
                recipeDTO.setNumberPeople(recipe.getNumberPeople());
                recipeDTO.setDescription(recipe.getDescription());
                recipeDTO.setCategoryTitle(recipe.getIdCategory().getTitle());
                recipeDTO.setNutritionTitle(recipe.getIdNutrition().getTitle());
                recipeDTO.setPicture(recipe.getPicture());
                recipeDTO.setDuration(recipe.getDuration());
                recipeDTO.setSeen(recipe.getSeen());

                constitutedDTO = new ConstitutedDTO();
                constitutedDTO.setRecipe(recipeDTO);
                constitutedDTO.setIngredients(new ArrayList<>()); // Initialise la liste d'ingrédients

                constitutedMap.put(recipeId, constitutedDTO);
                allConstituted.add(constitutedDTO); // Ajoutez à la liste finale
            }

            // Maintenant, créez l'ingrédient
            Ingredient ingredient = constituted.getIdIngredient();
            IngredientDTO ingredientDTO = new IngredientDTO();
            ingredientDTO.setId(ingredient.getId());
            ingredientDTO.setTitle(ingredient.getTitle());
            ingredientDTO.setCalorie(ingredient.getCalorie());
            ingredientDTO.setQuantity(constituted.getQuantity()); // Récupération de la quantité

            IngredientTypeDTO ingredientTypeDTO = new IngredientTypeDTO();
            ingredientTypeDTO.setId(ingredient.getIdIngredientCategory().getId());
            ingredientTypeDTO.setTitle(ingredient.getIdIngredientCategory().getTitle());
            ingredientDTO.setIngredientType(ingredientTypeDTO);

            // Ajoutez l'ingrédient à la recette
            constitutedDTO.getIngredients().add(ingredientDTO); // Assurez-vous que la méthode pour récupérer les ingrédients est correcte
        }

        return allConstituted;
    }


    @GetMapping("/{id}")
    public RecipeDTO getRecipeById(@PathVariable Integer id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found with id: " + id));

        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setId(recipe.getId());
        recipeDTO.setTitle(recipe.getTitle());
        recipeDTO.setNumberPeople(recipe.getNumberPeople());
        recipeDTO.setDescription(recipe.getDescription());
        recipeDTO.setCategoryTitle(recipe.getIdCategory().getTitle());
        recipeDTO.setNutritionTitle(recipe.getIdNutrition().getTitle());
        recipeDTO.setPicture(recipe.getPicture());
        recipeDTO.setDuration(recipe.getDuration());
        recipeDTO.setSeen(recipe.getSeen());
        for(Constituted co : constitutedService.findByIdRecipe(3)) {
            System.out.println(co.getIdIngredient().getTitle());
        }
//        List<IngredientDTO> ingredientDTOs = new ArrayList<>();
//        List<Constituted> constitutedList = constitutedService.findByRecipeId(id); // Méthode à créer dans le service
//
//        for (Constituted constituted : constitutedList) {
//            Ingredient ingredient = constituted.getIdIngredient();
//
//            IngredientDTO ingredientDTO = new IngredientDTO();
//            ingredientDTO.setId(ingredient.getId());
//            ingredientDTO.setTitle(ingredient.getTitle());
//            ingredientDTO.setCalorie(ingredient.getCalorie());
//            ingredientDTO.setQuantity(constituted.getQuantity());
//
//            // Récupérer le type d'ingrédient
//            IngredientTypeDTO ingredientTypeDTO = new IngredientTypeDTO();
//            ingredientTypeDTO.setId(ingredient.getIdIngredientCategory().getId());
//            ingredientTypeDTO.setTitle(ingredient.getIdIngredientCategory().getTitle());
//            ingredientDTO.setIngredientType(ingredientTypeDTO);
//
//            // Ajouter l'ingrédient au DTO de la recette
//            ingredientDTOs.add(ingredientDTO);
//        }

        // Ajouter la liste des ingrédients au DTO de la recette
//        recipeDTO.setIngredients(ingredientDTOs);
        return recipeDTO;
    }



}
