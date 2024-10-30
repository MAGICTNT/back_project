package fr.lgrb.back_project.service;

import fr.lgrb.back_project.entity.Constituted;
import fr.lgrb.back_project.entity.ConstitutedId;
import fr.lgrb.back_project.repository.ConstitutedRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConstitutedService {
    private final ConstitutedRepository constitutedRepository;

    public ConstitutedService(ConstitutedRepository constitutedRepository) {
        this.constitutedRepository = constitutedRepository;
    }

    public List<Constituted> findAll() {
        return constitutedRepository.findAll();
    }

    public Optional<Constituted> findById(ConstitutedId id) {
        return constitutedRepository.findById(id);
    }

    public List<Constituted> findByIdRecipe(int id){
        return constitutedRepository.findAllByIdRecipe_Id(id);
    }


    public Constituted save(Constituted constituted) {
        return constitutedRepository.save(constituted);
    }

    public List<Constituted> findByRecipeId(int recipeId) {
        return constitutedRepository.findAllByIdRecipe_Id(recipeId);
    }

    public void deleteById(ConstitutedId id) {
        constitutedRepository.deleteById(id);
    }
}
