package fr.lgrb.back_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "constituted")
public class Constituted {
    @EmbeddedId
    private ConstitutedId id;

    @MapsId("idIngredient")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_ingredient", nullable = false)
    private Ingredient idIngredient;

    @MapsId("idRecipe")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_recipe", nullable = false)
    private Recipe idRecipe;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

}