package fr.lgrb.back_project.service;

import fr.lgrb.back_project.entity.Allergy;
import fr.lgrb.back_project.entity.AllergyId;
import fr.lgrb.back_project.repository.AllergyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AllergyService {
    private final AllergyRepository allergyRepository;

    public AllergyService(AllergyRepository allergyRepository) {
        this.allergyRepository = allergyRepository;
    }

    public List<Allergy> findAll() {
        return allergyRepository.findAll();
    }

    public Optional<Allergy> findById(AllergyId id) {
        return allergyRepository.findById(id);
    }

    public List<Allergy> findByConsumerId(String pseudo ){
        return allergyRepository.findByIdConsumer_Pseudo(pseudo);
    }

    public Allergy save(Allergy allergy) {
        return allergyRepository.save(allergy);
    }

    public void deleteById(AllergyId id) {
        allergyRepository.deleteById(id);
    }
}
