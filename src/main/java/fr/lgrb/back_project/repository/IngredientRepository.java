package fr.lgrb.back_project.repository;

import fr.lgrb.back_project.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
}
