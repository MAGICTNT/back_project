package fr.lgrb.back_project.repository;

import fr.lgrb.back_project.entity.Constituted;
import fr.lgrb.back_project.entity.ConstitutedId;
import fr.lgrb.back_project.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConstitutedRepository extends JpaRepository<Constituted, ConstitutedId> {
    List<Constituted> findAllByIdRecipe_Id(int id);
}
