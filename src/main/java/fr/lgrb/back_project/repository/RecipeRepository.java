package fr.lgrb.back_project.repository;

import fr.lgrb.back_project.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    Recipe getRecipesByTitle(String title);

    @Query("SELECT r FROM Recipe r " +
            "JOIN Constituted c ON c.idRecipe.id = r.id " +
            "JOIN Ingredient i ON c.idIngredient.id = i.id " +
            "JOIN IngredientCategory ic ON i.idIngredientCategory.id = ic.id " +
            "WHERE (:title IS NULL OR r.title LIKE %:title%) " +
            "AND (:nutritionId IS NULL OR r.idNutrition.id = :nutritionId) " +
            "AND (:categoryId IS NULL OR r.idCategory.id = :categoryId) " +
            "AND (:allergenIds IS NULL OR ic.id IN :allergenIds) " +
            "GROUP BY r.id")
    List<Recipe> findRecipesByCriteria(
            @Param("title") String title,
            @Param("allergenIds") List<Integer> allergenIds,
            @Param("nutritionId") Integer nutritionId,
            @Param("categoryId") Integer categoryId);
}
