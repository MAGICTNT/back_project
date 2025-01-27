package fr.lgrb.back_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "ingredient_category")
public class IngredientCategory {
    @Id
    @ColumnDefault("nextval('ingredient_category_id_ingredient_category_seq')")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingredient_category", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

}