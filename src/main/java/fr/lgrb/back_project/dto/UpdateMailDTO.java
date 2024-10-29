package fr.lgrb.back_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMailDTO {
    private String mail;
    private String passsword;
}
