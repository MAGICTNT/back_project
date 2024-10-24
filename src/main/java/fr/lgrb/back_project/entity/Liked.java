package fr.lgrb.back_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "liked")
public class Liked {
    @EmbeddedId
    private LikedId id;

    @MapsId("idConsumer")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_consumer", nullable = false)
    private Consumer idConsumer;

    @MapsId("idNutrition")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_nutrition", nullable = false)
    private Nutrition idNutrition;

}