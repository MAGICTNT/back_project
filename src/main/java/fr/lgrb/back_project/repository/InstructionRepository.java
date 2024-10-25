package fr.lgrb.back_project.repository;

import fr.lgrb.back_project.entity.Instruction;
import fr.lgrb.back_project.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstructionRepository extends JpaRepository<Instruction, Integer> {
    public List<Instruction> getAllByIdRecipe(Recipe idRecipe);
}
