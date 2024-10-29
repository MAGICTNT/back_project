package fr.lgrb.back_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikedUpdateDTO {
    private String pseudo;
    private Liked oldLiked;
    private Liked newLiked;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Liked{
        private int id;
        private String title;
    }
}
