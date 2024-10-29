package fr.lgrb.back_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConstitutedRecipeDTO {
    private int id ;
    private String title ;
    private int numberPeople ;
    private int duration ;
    private String description ;
    private String picture ;
    private int seen ;
    private int idNutrition ;
    private int idCategory ;
    private List<IngredientDTO> ingredients; // Renommez cette propriété en "ingredient" si c'est un seul ingrédient.

}
