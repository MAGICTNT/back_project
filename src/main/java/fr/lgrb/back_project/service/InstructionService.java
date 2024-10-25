package fr.lgrb.back_project.service;

import fr.lgrb.back_project.entity.Instruction;
import fr.lgrb.back_project.entity.Recipe;
import fr.lgrb.back_project.repository.InstructionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructionService {

    private final InstructionRepository instructionRepository;

    @Autowired
    public InstructionService(InstructionRepository instructionRepository) {
        this.instructionRepository = instructionRepository;
    }

    public Instruction createInstruction(Instruction instruction) {
        return instructionRepository.save(instruction);
    }

    public List<Instruction> getAllInstructions() {
        return instructionRepository.findAll();
    }

    public List<Instruction> getAllInstructionByIdRecipe(Recipe recipe){
        return instructionRepository.getAllByIdRecipe(recipe);
    }

    public Instruction getInstructionById(Integer id) {
        Optional<Instruction> instruction = instructionRepository.findById(id);
        return instruction.orElseThrow(() -> new RuntimeException("Instruction not found with id " + id));
    }

    public Instruction updateInstruction(Integer id, Instruction instructionDetails) {
        Instruction existingInstruction = getInstructionById(id);
        existingInstruction.setDescription(instructionDetails.getDescription());
        return instructionRepository.save(existingInstruction);
    }

    public void deleteInstruction(Integer id) {
        instructionRepository.deleteById(id);
    }
}
