package fr.lgrb.back_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @ColumnDefault("nextval('recipe_id_recipe_seq')")
    @Column(name = "id_recipe", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "number_people", nullable = false)
    private Integer numberPeople;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "seen", nullable = false)
    private Integer seen;

    @Column(name = "picture", length = Integer.MAX_VALUE)
    private String picture;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_nutrition", nullable = false)
    @JsonIgnore
    private Nutrition idNutrition;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_category", nullable = false)
    @JsonIgnore
    private Category idCategory;

}