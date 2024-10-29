package fr.lgrb.back_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllergySendDTO {
    private String pseudo;
    private IngredientCategory ingredientCategory;

//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//    public class Consumer{
//        private String pseudo;
//    }
//
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class IngredientCategory{
        private int id;
        private String title;
}
}
