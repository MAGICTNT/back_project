package fr.lgrb.back_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeReciveAdvanceDTO {
    private int id;
    private String title;
    private int idCategory;
    private String picture;
    private int duration;
    private int numberPeople;
    private String description;
    private int idNutrition;
    private int seen;
    private List<Ingredient> ingredients = new ArrayList<>();
    private List<InstructionTextDTO> instructions = new ArrayList<>();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Ingredient {
        private int id;
        private String title;
        private int quantity;
    }

    public void addIngredient(Ingredient ingredient) {
        getIngredients().add(ingredient);
    }

    public void addInstruction(String instruction) {
        // Logique pour ajouter une instruction à la recette
    }
}
