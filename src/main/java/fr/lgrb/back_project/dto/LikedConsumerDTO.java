package fr.lgrb.back_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikedConsumerDTO {
    private String pseudo;
    private List<Map<Integer, String>> listLiked;
}
