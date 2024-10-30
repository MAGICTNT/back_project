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
public class RecipeSearchDTO {
    private String title;
    private List<Integer> allergys;
    private Integer nutritionId;
    private Integer categoryId;
}
