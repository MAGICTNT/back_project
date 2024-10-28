package fr.lgrb.back_project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonFormat
public class IngredientDTO {
    private int id;
    private String title;
    private String calorie;
    private IngredientTypeDTO ingredientType;
    private Integer quantity; // Ajout du champ quantity

}
