package fr.lgrb.back_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerInscriptionDTO {
    private Map<Integer, String > pseudo;
    private Map<Integer, String > mail;
    private String role;
}
