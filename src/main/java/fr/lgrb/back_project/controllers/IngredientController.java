package fr.lgrb.back_project.controllers;

import fr.lgrb.back_project.dto.IngredientDTO;
import fr.lgrb.back_project.dto.IngredientSendDTO;
import fr.lgrb.back_project.dto.IngredientTypeDTO;
import fr.lgrb.back_project.entity.Ingredient;
import fr.lgrb.back_project.entity.IngredientCategory;
import fr.lgrb.back_project.service.IngredientCategoryService;
import fr.lgrb.back_project.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/ingredient")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private IngredientCategoryService ingredientCategoryService;

    @GetMapping("/all")
    public List<IngredientSendDTO> getAllIngredient(){
        List<IngredientSendDTO> ingredientDTOS = new ArrayList<>();
        for(Ingredient ingredient : ingredientService.getAllIngredients()){
            IngredientSendDTO addIndredient = new IngredientSendDTO();
            addIndredient.setId(ingredient.getId());
            addIndredient.setTitle(ingredient.getTitle());
            ingredientDTOS.add(addIndredient);
        }
        return ingredientDTOS;
    }
}
