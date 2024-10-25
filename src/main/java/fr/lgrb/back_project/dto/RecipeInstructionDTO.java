package fr.lgrb.back_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeInstructionDTO {
    private int id;
    private String title;
    private int numberPeople;
    private int duration;
    private String description;
    private int seen;
    private String picture;
    private String nutrition;
    private String category;
    private Map<Integer, String> instructions;
}
