package fr.lgrb.back_project.controllers;

import fr.lgrb.back_project.dto.AllergyConsumerDTO;
import fr.lgrb.back_project.dto.AllergySendDTO;
import fr.lgrb.back_project.dto.ConsumerReceivedDTO;
import fr.lgrb.back_project.entity.*;
import fr.lgrb.back_project.repository.AllergyRepository;
import fr.lgrb.back_project.service.AllergyService;
import fr.lgrb.back_project.service.ConsumerService;
import fr.lgrb.back_project.service.IngredientCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/allergy")
public class AllergyController {

    @Autowired
    ConsumerService consumerService;
    @Autowired
    AllergyService allergyService;
    @Autowired
    IngredientCategoryService ingredientCategoryService;

    @PostMapping("/all")
    public AllergyConsumerDTO getAll(@RequestBody ConsumerReceivedDTO consumerReceivedDTO){
        System.out.println(consumerReceivedDTO.getLogin());

        Consumer consumer = consumerService.findByPseudo(consumerReceivedDTO.getLogin());
        System.out.println(consumer);

        List<Allergy> allergyList = allergyService.findByConsumerId(consumer.getPseudo());
        List<AllergyConsumerDTO> list = new ArrayList<>();

        AllergyConsumerDTO allergyConsumerDTO = new AllergyConsumerDTO();
        allergyConsumerDTO.setPseudo(consumer.getPseudo());
        List<Map<Integer,String>> listCategory = new ArrayList<>();

        for(Allergy allergy : allergyList){
            Map<Integer, String> category = new HashMap<>();

            category.put(allergy.getIdIngredientCategory().getId(), allergy.getIdIngredientCategory().getTitle());
            listCategory.add(category);
        }

        allergyConsumerDTO.setListIngredientCategory(listCategory);
        return allergyConsumerDTO;
    }

    @PostMapping("/new")
    public Map<Integer, String> newAllergy(@RequestBody AllergySendDTO allergySendDTO){
        Consumer consumer = consumerService.findByPseudo(allergySendDTO.getPseudo());
        IngredientCategory ingredientCategory = ingredientCategoryService.getIngredientCategoryById(allergySendDTO.getIngredientCategory().getId());
        Map<Integer, String> response = new HashMap<>();

        if(consumer == null){
            response.put(404, "user not found");
            return response;
        }
        if(ingredientCategory == null){
            response.put(404, "ingredient category not found");
            return response;
        }
        Role role = new Role();
        role.setId(consumer.getIdRole().getId());
        consumer.setIdRole(role);

        AllergyId allergyId = new AllergyId();
        allergyId.setIdConsumer(consumer.getId());
        allergyId.setIdIngredientCategory(ingredientCategory.getId());

        Allergy allergy = new Allergy();
        allergy.setId(allergyId);
        allergy.setIdConsumer(consumer);
        allergy.setIdIngredientCategory(ingredientCategory);

        allergyService.save(allergy);

        response.put(200, "create");
        return response;
    }

    @DeleteMapping("/delete")
    public Map<Integer, String> delete(@RequestBody AllergySendDTO allergySendDTO){
        Consumer consumer = consumerService.findByPseudo(allergySendDTO.getPseudo());
        IngredientCategory ingredientCategory = ingredientCategoryService.getIngredientCategoryById(allergySendDTO.getIngredientCategory().getId());
        Map<Integer, String> response = new HashMap<>();
        if(consumer == null){
            response.put(404, "user not found");
            return response;
        }
        if(ingredientCategory == null){
            response.put(404, "ingredient category not found");
            return response;
        }
        AllergyId allergyId = new AllergyId();
        allergyId.setIdConsumer(consumer.getId());
        allergyId.setIdIngredientCategory(ingredientCategory.getId());

        allergyService.deleteById(allergyId);

        response.put(200, "delete");
        return response;
    }
}
