package fr.lgrb.back_project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ConstitutedId implements Serializable {
    private static final long serialVersionUID = -896514627979194477L;
    @Column(name = "id_ingredient", nullable = false)
    private Integer idIngredient;

    @Column(name = "id_recipe", nullable = false)
    private Integer idRecipe;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ConstitutedId entity = (ConstitutedId) o;
        return Objects.equals(this.idIngredient, entity.idIngredient) &&
                Objects.equals(this.idRecipe, entity.idRecipe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idIngredient, idRecipe);
    }

}