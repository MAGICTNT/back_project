package fr.lgrb.back_project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class LikedId implements Serializable {
    private static final long serialVersionUID = 2161445806767248513L;
    @Column(name = "id_consumer", nullable = false)
    private Integer idConsumer;

    @Column(name = "id_nutrition", nullable = false)
    private Integer idNutrition;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LikedId entity = (LikedId) o;
        return Objects.equals(this.idNutrition, entity.idNutrition) &&
                Objects.equals(this.idConsumer, entity.idConsumer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNutrition, idConsumer);
    }

}