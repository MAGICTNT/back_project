package fr.lgrb.back_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConstitutedDTO {
    private RecipeDTO recipe;
    private List<IngredientDTO> ingredients; // Renommez cette propriété en "ingredient" si c'est un seul ingrédient.
    private Integer quantity; // Cela peut représenter la quantité de l'ingrédient spécifique.
}
