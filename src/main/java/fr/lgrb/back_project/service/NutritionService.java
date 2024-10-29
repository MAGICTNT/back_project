package fr.lgrb.back_project.service;

import fr.lgrb.back_project.entity.Nutrition;
import fr.lgrb.back_project.entity.Nutrition;
import fr.lgrb.back_project.repository.NutritionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NutritionService {
    private final NutritionRepository nutritionRepository;

    @Autowired
    public NutritionService(NutritionRepository nutritionRepository) {
        this.nutritionRepository = nutritionRepository;
    }
    
    public Nutrition createNutrition(Nutrition nutrition){
        return nutritionRepository.save(nutrition);
    }

    public List<Nutrition> getAllNutritions() {
        return nutritionRepository.findAll();
    }

    public Nutrition findByIdAndTitle(int id , String title){
        return nutritionRepository.findByIdAndTitle(id, title);
    }

    public Nutrition getNutritionById(Integer id) {
        Optional<Nutrition> nutrition = nutritionRepository.findById(id);
        return nutrition.orElseThrow(() -> new RuntimeException("Nutrition not found with id " + id));
    }

    public Nutrition updateNutrition(Integer id, Nutrition nutritionDetails) {
        Nutrition existingNutrition = getNutritionById(id);

        return nutritionRepository.save(existingNutrition);
    }

    public void deleteNutrition(Integer id) {
        nutritionRepository.deleteById(id);
    }
}
