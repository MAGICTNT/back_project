package fr.lgrb.back_project.repository;

import fr.lgrb.back_project.entity.Allergy;
import fr.lgrb.back_project.entity.AllergyId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AllergyRepository extends JpaRepository<Allergy, AllergyId> {
    List<Allergy> findByIdConsumer_Pseudo(String pseudo);
}
