package fr.lgrb.back_project.repository;

import fr.lgrb.back_project.entity.IngredientCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory , Integer> {
}
