package fr.lgrb.back_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikedDTO {
    public Map<Integer, String> consumer;
    public Map<Integer, String> nutrition;

    public Map<Integer, String> getConsumer() {
        return consumer;
    }

    public void setConsumer(Map<Integer, String> consumer) {
        this.consumer = consumer;
    }

    public Map<Integer, String> getNutrition() {
        return nutrition;
    }

    public void setNutrition(Map<Integer, String> nutrition) {
        this.nutrition = nutrition;
    }
}
