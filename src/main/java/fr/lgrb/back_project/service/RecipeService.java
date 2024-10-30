package fr.lgrb.back_project.service;

import fr.lgrb.back_project.dto.RecipeReciveDTO;
import fr.lgrb.back_project.entity.Instruction;
import fr.lgrb.back_project.entity.Recipe;
import fr.lgrb.back_project.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    @Autowired
    private ConstitutedService constitutedService;

    @Autowired
    private InstructionService instructionService;
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

    public List<RecipeReciveDTO> searchRecipes(String title, List<Integer> allergenIds, Integer nutritionId, Integer categoryId) {

        List<Recipe> recipes = recipeRepository.findRecipesByCriteria(title, allergenIds, nutritionId, categoryId);
        return recipes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private RecipeReciveDTO convertToDto(Recipe recipe) {
        RecipeReciveDTO dto = new RecipeReciveDTO();

        dto.setTitle(recipe.getTitle());
        dto.setNumberPeople(recipe.getNumberPeople());
        dto.setDuration(recipe.getDuration());
        dto.setDescription(recipe.getDescription());
        dto.setSeen(recipe.getSeen());
        dto.setPicture(recipe.getPicture());
        dto.setNutritionTitle(recipe.getIdNutrition().getId());
        dto.setCategoryTitle(recipe.getIdCategory().getId());

        // Récupération des ingrédients associés via constitutedService
        List<RecipeReciveDTO.Ingredient> ingredientDTOs = constitutedService.findByRecipeId(recipe.getId()).stream()
                .map(constituted -> new RecipeReciveDTO.Ingredient(
                        constituted.getIdIngredient().getId(),
                        constituted.getIdIngredient().getTitle(),
                        constituted.getQuantity()))
                .collect(Collectors.toList());
        dto.setIngredients(ingredientDTOs);

        // Récupération des instructions associées via instructionService
        List<String> instructionTexts = instructionService.findByRecipeId(recipe.getId()).stream()
                .map(Instruction::getDescription)
                .collect(Collectors.toList());
        dto.setInstructions(instructionTexts);

        return dto;
    }

}
