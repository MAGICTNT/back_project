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
public class AllergyId implements Serializable {
    private static final long serialVersionUID = 2307734611805771637L;
    @Column(name = "id_ingredient_category", nullable = false)
    private Integer idIngredientCategory;

    @Column(name = "id_consumer", nullable = false)
    private Integer idConsumer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AllergyId entity = (AllergyId) o;
        return Objects.equals(this.idIngredientCategory, entity.idIngredientCategory) &&
                Objects.equals(this.idConsumer, entity.idConsumer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idIngredientCategory, idConsumer);
    }

}