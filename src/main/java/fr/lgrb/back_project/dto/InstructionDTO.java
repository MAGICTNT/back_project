package fr.lgrb.back_project.dto;


import fr.lgrb.back_project.entity.Recipe;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstructionDTO {
    private int id;
    private String description;
    private Recipes recipes;

    public void setRecipe(Recipe recipe) {
        Recipes recipes1 = new Recipes();
        recipes1.setId(recipe.getId());
        recipes1.setTitle(recipe.getTitle());
        this.recipes = recipes1;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Recipes{
        private int id;
        private String title;
        private Integer numberPeople;
        private Integer duration;
        private String description;
        private Integer seen;
        private String picture;
        private Nutrition nutrition;
        private Category category;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Nutrition{
            private Integer id;
            private String title;
        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Category{
            private Integer id;
            private String title;
            private String description;
        }
    }

}
