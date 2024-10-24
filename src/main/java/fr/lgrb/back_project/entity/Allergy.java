package fr.lgrb.back_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "allergy")
public class Allergy {
    @EmbeddedId
    private AllergyId id;

    @MapsId("idIngredientCategory")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_ingredient_category", nullable = false)
    private IngredientCategory idIngredientCategory;

    @MapsId("idConsumer")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_consumer", nullable = false)
    private Consumer idConsumer;

}