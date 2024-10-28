package fr.lgrb.back_project.controllers;

import fr.lgrb.back_project.dto.InstructionDTO;
import fr.lgrb.back_project.dto.RecipeInstructionDTO;
import fr.lgrb.back_project.entity.Instruction;
import fr.lgrb.back_project.entity.Recipe;
import fr.lgrb.back_project.repository.InstructionRepository;
import fr.lgrb.back_project.repository.RecipeRepository;
import fr.lgrb.back_project.service.InstructionService;
import fr.lgrb.back_project.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/instruction")
public class InstructionController {
    @Autowired
    private InstructionRepository instructionRepository;
    @Autowired
    private RecipeRepository recipeRepository;


    @Autowired
    private InstructionService instructionService;
    @Autowired
    private RecipeService recipeService;

    @GetMapping("/all")
    public List<InstructionDTO> getAllInstruction(){
        List<InstructionDTO> allInstruction = new ArrayList<>();
        List<Instruction> instructions = instructionService.getAllInstructions();
        for(Instruction instruction : instructions){
            InstructionDTO newInstructionDto = new InstructionDTO();
            newInstructionDto.setId(instruction.getId());
            newInstructionDto.setDescription(instruction.getDescription());
            System.out.println(recipeRepository.getReferenceById(instruction.getIdRecipe().getId()));
            System.out.println("------");
            Recipe newRecipe = recipeRepository.getReferenceById(instruction.getIdRecipe().getId());
            System.out.println(newRecipe.getId());

            newInstructionDto.setRecipe(newRecipe);
            allInstruction.add(newInstructionDto);
        }
        return allInstruction;
    }

    @GetMapping("/{id}")
    public RecipeInstructionDTO getAllInfoRecipeByIdInsctuction(@PathVariable int id){
        Instruction instruction = instructionService.getInstructionById(id);
        Recipe recipe = recipeService.getRecipeById(instruction.getId());
        RecipeInstructionDTO recipeInstructionDTO = new RecipeInstructionDTO();
        recipeInstructionDTO.setId(recipe.getId());
        recipeInstructionDTO.setTitle(recipe.getTitle());
        recipeInstructionDTO.setNumberPeople(recipe.getNumberPeople());
        recipeInstructionDTO.setDuration(recipe.getDuration());
        recipeInstructionDTO.setDescription(recipe.getDescription());
        recipeInstructionDTO.setSeen(recipe.getSeen());
        recipeInstructionDTO.setPicture(recipe.getPicture());
        recipeInstructionDTO.setNutrition(recipe.getIdNutrition().getTitle());
        recipeInstructionDTO.setCategory(recipe.getIdCategory().getTitle());
        List<Instruction> instructionList = instructionService.getAllInstructionByIdRecipe(recipe);
        Map<Integer, String > mapInstruction = new HashMap<>();
        for(Instruction inst : instructionList){
            mapInstruction.put(inst.getId(), inst.getDescription());
        }
        recipeInstructionDTO.setInstructions(mapInstruction);
        return recipeInstructionDTO;
    }

}
