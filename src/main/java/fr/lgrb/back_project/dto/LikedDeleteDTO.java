package fr.lgrb.back_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikedDeleteDTO {
    private String pseudo;
    private Nutrition nutrition;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Nutrition{
        private int id;
        private String title;
    }

}
