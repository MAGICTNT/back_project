package fr.lgrb.back_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "nutrition")
public class Nutrition {
    @Id
    @ColumnDefault("nextval('nutrition_id_nutrition_seq')")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nutrition", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false, length = 150)
    private String title;

}