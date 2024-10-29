package fr.lgrb.back_project.repository;

import fr.lgrb.back_project.entity.Nutrition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NutritionRepository extends JpaRepository<Nutrition, Integer> {
    Nutrition findByIdAndTitle(int id, String title);

}
