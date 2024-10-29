package fr.lgrb.back_project.controllers;

import fr.lgrb.back_project.entity.Nutrition;
import fr.lgrb.back_project.service.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/nutrition")
public class NutritionController {

    @Autowired
    private NutritionService nutritionService;

    @GetMapping("/all")
    public List<Nutrition> getAll(){
        return nutritionService.getAllNutritions();
    }


}
