package fr.lgrb.back_project.controllers;

import fr.lgrb.back_project.dto.*;
import fr.lgrb.back_project.entity.*;

import fr.lgrb.back_project.error.ResourceNotFoundException;
import fr.lgrb.back_project.repository.CategoryRepository;
import fr.lgrb.back_project.repository.NutritionRepository;
import fr.lgrb.back_project.repository.RecipeRepository;
import fr.lgrb.back_project.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private NutritionService nutritionService;
    @Autowired
    private InstructionService instructionService;

    @GetMapping("/all")
    public List<RecipeSimpleDTO> getAllRecipes() {
        List<RecipeSimpleDTO> allRecipe = new ArrayList<>();
        List<Recipe> recipes = recipeRepository.findAll();
        for(Recipe recipe: recipes ){
            RecipeSimpleDTO newRecipeDto = new RecipeSimpleDTO();
            newRecipeDto.setId(recipe.getId());
            newRecipeDto.setTitle(recipe.getTitle());
            newRecipeDto.setNumberPeople(recipe.getNumberPeople());
            newRecipeDto.setDescription(recipe.getDescription());
            newRecipeDto.setIdCategory(recipe.getIdCategory().getId()); // faire la récupération
            newRecipeDto.setIdNutrition(recipe.getIdNutrition().getId());
            newRecipeDto.setPicture(recipe.getPicture());
            newRecipeDto.setDuration(recipe.getDuration());
            newRecipeDto.setSeen(recipe.getSeen());
            allRecipe.add(newRecipeDto);
        }
        return allRecipe;
    }

//    @GetMapping("/all")
//    public List<ConstitutedDTO> getFullRecipes() {
//        List<ConstitutedDTO> allConstituted = new ArrayList<>();
//        Map<Integer, ConstitutedDTO> constitutedMap = new HashMap<>();
//
//        for (Constituted constituted : constitutedService.findAll()) {
//            Recipe recipe = constituted.getIdRecipe();
//            Integer recipeId = recipe.getId();
//
//            ConstitutedDTO constitutedDTO;
//
//            // Si la recette existe déjà dans le map, récupérez-la
//            if (constitutedMap.containsKey(recipeId)) {
//                constitutedDTO = constitutedMap.get(recipeId);
//            } else {
//                // Sinon, créez une nouvelle entrée pour la recette
//                RecipeDTO recipeDTO = new RecipeDTO();
//                recipeDTO.setId(recipeId);
//                recipeDTO.setTitle(recipe.getTitle());
//                recipeDTO.setNumberPeople(recipe.getNumberPeople());
//                recipeDTO.setDescription(recipe.getDescription());
//                recipeDTO.setCategoryTitle(recipe.getIdCategory().getTitle());
//                recipeDTO.setNutritionTitle(recipe.getIdNutrition().getTitle());
//                recipeDTO.setPicture(recipe.getPicture());
//                recipeDTO.setDuration(recipe.getDuration());
//                recipeDTO.setSeen(recipe.getSeen());
//
//                constitutedDTO = new ConstitutedDTO();
//                constitutedDTO.setRecipe(recipeDTO);
//                constitutedDTO.setIngredients(new ArrayList<>()); // Initialise la liste d'ingrédients
//
//                constitutedMap.put(recipeId, constitutedDTO);
//                allConstituted.add(constitutedDTO); // Ajoutez à la liste finale
//            }
//
//            // Maintenant, créez l'ingrédient
//            Ingredient ingredient = constituted.getIdIngredient();
//            IngredientDTO ingredientDTO = new IngredientDTO();
//            ingredientDTO.setId(ingredient.getId());
//            ingredientDTO.setTitle(ingredient.getTitle());
//            ingredientDTO.setCalorie(ingredient.getCalorie());
//            ingredientDTO.setQuantity(constituted.getQuantity()); // Récupération de la quantité
//
//            IngredientTypeDTO ingredientTypeDTO = new IngredientTypeDTO();
//            ingredientTypeDTO.setId(ingredient.getIdIngredientCategory().getId());
//            ingredientTypeDTO.setTitle(ingredient.getIdIngredientCategory().getTitle());
//            ingredientDTO.setIngredientType(ingredientTypeDTO);
//
//            // Ajoutez l'ingrédient à la recette
//            constitutedDTO.getIngredients().add(ingredientDTO); // Assurez-vous que la méthode pour récupérer les ingrédients est correcte
//        }
//
//        return allConstituted;
//    }


    @GetMapping("/{id}")
    public RecipeDTO getRecipeById(@PathVariable Integer id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found with id: " + id));
        List<Instruction> instructionRecipe = instructionService.findByRecipeId(id);
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

        List<IngredientDTO> ingredientDTOs = new ArrayList<>();
        List<Constituted> constitutedList = constitutedService.findByIdRecipe(id); // Méthode à créer dans le service

        for (Constituted constituted : constitutedList) {
            Ingredient ingredient = constituted.getIdIngredient();

            IngredientDTO ingredientDTO = new IngredientDTO();
            ingredientDTO.setId(ingredient.getId());
            ingredientDTO.setTitle(ingredient.getTitle());
            ingredientDTO.setCalorie(ingredient.getCalorie());
            ingredientDTO.setQuantity(constituted.getQuantity());

            // Récupérer le type d'ingrédient
            IngredientTypeDTO ingredientTypeDTO = new IngredientTypeDTO();
            ingredientTypeDTO.setId(ingredient.getIdIngredientCategory().getId());
            ingredientTypeDTO.setTitle(ingredient.getIdIngredientCategory().getTitle());
            ingredientDTO.setIngredientType(ingredientTypeDTO);

            // Ajouter l'ingrédient au DTO de la recette
            ingredientDTOs.add(ingredientDTO);
        }
        recipeDTO.setIngredients(ingredientDTOs);

        List<InstructionDTO> instructionDTOList = new ArrayList<>();

        for(Instruction instruction : instructionRecipe){
            InstructionDTO instructionDTO = new InstructionDTO();
            instructionDTO.setId(instruction.getId());
            instructionDTO.setDescription(instruction.getDescription());
            instructionDTOList.add(instructionDTO);
        }

        recipeDTO.setInstructions(instructionDTOList);
        return recipeDTO;
    }

//    @PostMapping("/new")
//    public RecipeReciveDTO newRecipe(@RequestBody RecipeReciveDTO recipeReciveDTO) {
//        Recipe newRecipe = new Recipe();
//        int idNewRecipe = recipeService.getAllRecipes().size() +1;
//        newRecipe.setId(idNewRecipe);
//        newRecipe.setTitle(recipeReciveDTO.getTitle());
//        newRecipe.setDuration(recipeReciveDTO.getDuration());
//        newRecipe.setSeen(recipeReciveDTO.getSeen());
//        newRecipe.setPicture(recipeReciveDTO.getPicture());
//        newRecipe.setNumberPeople(recipeReciveDTO.getNumberPeople());
//        newRecipe.setIdCategory(categoryService.getCategoryById(1)); // Remplacez 1 par la valeur appropriée
//        newRecipe.setIdNutrition(nutritionService.getNutritionById(1)); // Remplacez 1 par la valeur appropriée
//
//        // Ajouter les ingrédients
//        for (RecipeReciveDTO.Ingredient ingredientDTO : recipeReciveDTO.getIngredients()) {
//            Ingredient newIngredient = new Ingredient();
//            newIngredient.setId(ingredientDTO.getId());
//            newIngredient.setTitle(ingredientDTO.getTitle());
//            ConstitutedId constitutedId = new ConstitutedId(ingredientDTO.getId(), idNewRecipe);
//            Constituted constituted = new Constituted();
//            constituted.setId(constitutedId);
//            constituted.setQuantity(1111);
//            System.out.println(constituted.getIdRecipe() + " <- id recipe");
//            System.out.println(constituted.getId()  + " <- constituted id");
//            System.out.println(constituted.getQuantity() + " <- quantity");
////            constitutedService.save(constituted);
//        }
//        Instruction newInstruction = new Instruction();
//        newInstruction.setIdRecipe(newRecipe);
//        // Ajouter les instructions
//        for (String instructionText : recipeReciveDTO.getInstructions()) {
//            newInstruction.setId(instructionService.getAllInstructions().size() + 1);
//            newInstruction.setDescription(instructionText);
////            instructionService.createInstruction(newInstruction);
//
//        }
//
//
//        // Sauvegarder la recette (si nécessaire)
////        recipeService.createRecipe(newRecipe);
//
//        return recipeReciveDTO;
//    }

    @PostMapping("/new")
    public RecipeReciveDTO newRecipe(@RequestBody RecipeReciveDTO recipeReciveDTO) {
        Recipe newRecipe = new Recipe();
        newRecipe.setTitle(recipeReciveDTO.getTitle());
        newRecipe.setDuration(recipeReciveDTO.getDuration());
        newRecipe.setSeen(recipeReciveDTO.getSeen());
        newRecipe.setPicture(recipeReciveDTO.getPicture());
        newRecipe.setNumberPeople(recipeReciveDTO.getNumberPeople());
        newRecipe.setIdCategory(categoryService.getCategoryById(recipeReciveDTO.getCategoryTitle()));
        newRecipe.setIdNutrition(nutritionService.getNutritionById(recipeReciveDTO.getNutritionTitle()));

        newRecipe = recipeService.createRecipe(newRecipe);

        for (RecipeReciveDTO.Ingredient ingredientDTO : recipeReciveDTO.getIngredients()) {
            Ingredient newIngredient = ingredientService.getIngredientById(ingredientDTO.getId());
            ConstitutedId constitutedId = new ConstitutedId(ingredientDTO.getId(), newRecipe.getId());
            Constituted constituted = new Constituted();
            constituted.setId(constitutedId);
            constituted.setQuantity(ingredientDTO.getQuantity());
            constituted.setIdIngredient(newIngredient);
            constituted.setIdRecipe(newRecipe);
            constitutedService.save(constituted);
        }

        for (String instructionText : recipeReciveDTO.getInstructions()) {
            Instruction newInstruction = new Instruction();
            newInstruction.setDescription(instructionText);
            newInstruction.setIdRecipe(newRecipe);
            instructionService.createInstruction(newInstruction);
        }

        return recipeReciveDTO;
    }

    @PutMapping("/update")
    public RecipeReciveDTO updateRecipe(@RequestParam int id, @RequestBody RecipeReciveDTO recipeReciveDTO) {
        System.out.println("put id " + id);
        Recipe updateRecipe = recipeService.getRecipeById(id);
        RecipeReciveDTO updateRecipeDTO = new RecipeReciveDTO();
        if (updateRecipe == null) {
            updateRecipe.setTitle(recipeReciveDTO.getTitle());
            updateRecipe.setDuration(recipeReciveDTO.getDuration());
            updateRecipe.setSeen(recipeReciveDTO.getSeen());
            updateRecipe.setPicture(recipeReciveDTO.getPicture());
            updateRecipe.setNumberPeople(recipeReciveDTO.getNumberPeople());
            updateRecipe.setIdCategory(categoryService.getCategoryById(recipeReciveDTO.getCategoryTitle()));
            updateRecipe.setIdNutrition(nutritionService.getNutritionById(recipeReciveDTO.getNutritionTitle()));

            for (RecipeReciveDTO.Ingredient ingredientDTO : recipeReciveDTO.getIngredients()) {
                Ingredient newIngredient = ingredientService.getIngredientById(ingredientDTO.getId());
                ConstitutedId constitutedId = new ConstitutedId(ingredientDTO.getId(), updateRecipe.getId());
                Constituted constituted = new Constituted();
                constituted.setId(constitutedId);
                constituted.setQuantity(ingredientDTO.getQuantity());
                constituted.setIdIngredient(newIngredient);
                constituted.setIdRecipe(updateRecipe);
                constitutedService.save(constituted);
            }

            for (String instructionText : recipeReciveDTO.getInstructions()) {
                Instruction newInstruction = new Instruction();
                newInstruction.setDescription(instructionText);
                newInstruction.setIdRecipe(updateRecipe);
                instructionService.createInstruction(newInstruction);
            }

            return updateRecipeDTO;
        } else {
            return updateRecipeDTO;
        }

    }

    @GetMapping("/search")
    public ResponseEntity<List<RecipeReciveDTO>> searchRecipes(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) List<Integer> allergenIds,
            @RequestParam(required = false) Integer nutritionId,
            @RequestParam(required = false) Integer categoryId) {

        List<RecipeReciveDTO> recipes = recipeService.searchRecipes(title, allergenIds, nutritionId, categoryId);
        for(RecipeReciveDTO recipeReciveDTO : recipes){
            System.out.println(recipeReciveDTO.getTitle());
        }
        return ResponseEntity.ok(recipes);
    }

    @DeleteMapping("/delete")
    public Map<String, Boolean> deleteRecipe(@RequestParam int id) {
        Map<String, Boolean> response = new HashMap<>();
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found with id: " + id));
        try {

            for (Instruction instruction : instructionService.getAllInstructionByIdRecipe(recipe)) {
                instructionService.deleteInstruction(instruction.getId());
            }
            for (Constituted constituted : constitutedService.findByIdRecipe(recipe.getId())) {
                constitutedService.deleteById(constituted.getId());
            }

            recipeRepository.delete(recipe);
            response.put("deleted", Boolean.TRUE);
            return response;

        } catch (Exception e) {
            response.put("deleted", Boolean.FALSE);
            return response;
        }
    }

}
