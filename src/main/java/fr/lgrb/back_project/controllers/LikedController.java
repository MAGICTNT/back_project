package fr.lgrb.back_project.controllers;

import fr.lgrb.back_project.dto.*;
import fr.lgrb.back_project.entity.Consumer;
import fr.lgrb.back_project.entity.Liked;
import fr.lgrb.back_project.entity.LikedId;
import fr.lgrb.back_project.entity.Nutrition;
import fr.lgrb.back_project.service.ConsumerService;
import fr.lgrb.back_project.service.LikedService;
import fr.lgrb.back_project.service.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/liked")
public class LikedController {

    @Autowired
    NutritionService nutritionService;
    @Autowired
    ConsumerService consumerService;
    @Autowired
    LikedService likedService;

    @GetMapping("/all")
    public List<LikedDTO> getAll(){
        List<LikedDTO> likedDTOList = new ArrayList<>();

        for(Liked liked : likedService.findAll()){
            LikedDTO likedDTO = new LikedDTO();
            Map<Integer, String> consum = new HashMap<>();
            Map<Integer, String> nutri = new HashMap<>();
            consum.put(liked.getIdConsumer().getId(), liked.getIdConsumer().getPseudo());
            likedDTO.setConsumer(consum);
            nutri.put(liked.getIdNutrition().getId(), liked.getIdNutrition().getTitle());
            likedDTO.setNutrition(nutri);
            likedDTOList.add(likedDTO);
//            likedDTO.setConsumer(0, "");
            System.out.println("id consumer  :" + liked.getIdConsumer().getId());
            System.out.println("id nutrition :" + liked.getIdNutrition().getId());
            System.out.println("id general   :" + liked.getId());
            System.out.println("---------");
        }
        return likedDTOList;
    }

    @GetMapping("/byConsumer")
    public LikedConsumerDTO getAllAllergyConsumer(@RequestParam String pseudo){
        LikedConsumerDTO likedConsumerDTO = new LikedConsumerDTO();
        Consumer consumer = consumerService.findByPseudo(pseudo);
        likedConsumerDTO.setPseudo(consumer.getPseudo());
        List<Map<Integer, String>> listLiked = new ArrayList<>();
        for(Liked liked : likedService.findByConsumerId(consumer.getId())){
           Map<Integer, String> add = new HashMap<>();
           add.put(liked.getIdNutrition().getId(), liked.getIdNutrition().getTitle());
           listLiked.add(add);
        }
        likedConsumerDTO.setListLiked(listLiked);
        return likedConsumerDTO;
    }

    @PostMapping("/new")
    public Map<Integer, String> newNutritionForCustumer(@RequestParam String pseudoConsumer, @RequestParam int idNutrition){
        Consumer consumer = consumerService.findByPseudo(pseudoConsumer);
        Nutrition nutrition = nutritionService.getNutritionById(idNutrition);
        LikedDTO likedDTO = new LikedDTO();
        Map<Integer , String> response = new HashMap<>();
        if (consumer == null || nutrition == null){
            response.put(404, "Not Fount");
            return response;
        }

        LikedId likedId = new LikedId();
        likedId.setIdConsumer(consumer.getId());
        likedId.setIdNutrition(nutrition.getId());
        Liked liked = new Liked();
        liked.setId(likedId);
        liked.setIdNutrition(nutrition);
        liked.setIdConsumer(consumer);

        likedService.save(liked);

        response.put(200, "créate");
        return response;
    }

    @PutMapping("/update")
    public Map<Integer, String> update(@RequestBody LikedUpdateDTO likedUpdateDTO){
        Consumer consumer = consumerService.findByPseudo(likedUpdateDTO.getPseudo());
        System.out.println(likedUpdateDTO.getOldLiked().getId());
        Nutrition oldNutrition = nutritionService.findByIdAndTitle(likedUpdateDTO.getOldLiked().getId(), likedUpdateDTO.getOldLiked().getTitle());
        Nutrition newNutrition = nutritionService.findByIdAndTitle(likedUpdateDTO.getNewLiked().getId(), likedUpdateDTO.getNewLiked().getTitle());
        Map<Integer, String> response = new HashMap<>();
        if(consumer== null || oldNutrition == null || newNutrition == null){
            response.put(404, "not found");
            return response;
        }
        LikedId likedId = new LikedId();
        likedId.setIdConsumer(consumer.getId());
        likedId.setIdNutrition(newNutrition.getId());
        likedService.deleteById(likedId);

        Liked liked = new Liked();
        liked.setId(likedId);
        liked.setIdNutrition(newNutrition);
        liked.setIdConsumer(consumer);

        likedService.save(liked);

        response.put(200, "update");
        return response;

    }

    @DeleteMapping("/delete")
    public Map<Integer, String> delete(@RequestBody LikedDeleteDTO likedDeleteDTO){
        Map<Integer, String> response = new HashMap<>();
        Consumer consumer = consumerService.findByPseudo(likedDeleteDTO.getPseudo());
        Nutrition nutrition = nutritionService.findByIdAndTitle(likedDeleteDTO.getNutrition().getId(), likedDeleteDTO.getNutrition().getTitle());

        if (consumer == null){
            response.put(404, "consumer not found");
            return response;
        }
        if (nutrition == null){
            response.put(404, "nutrition not found");
            return response;
        }

        LikedId likedId = new LikedId();
        likedId.setIdNutrition(nutrition.getId());
        likedId.setIdConsumer(consumer.getId());
        likedService.deleteById(likedId);

        response.put(200, "delete");
        return response;
    }
}
