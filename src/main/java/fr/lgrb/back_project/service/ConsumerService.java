package fr.lgrb.back_project.service;

import fr.lgrb.back_project.dto.ConsumerDTO;
import fr.lgrb.back_project.entity.Consumer;
import fr.lgrb.back_project.entity.Role;
import fr.lgrb.back_project.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumerService {
    private final ConsumerRepository consumerRepository;

    @Autowired
    public ConsumerService(ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    public Consumer createConsumer(Consumer consumer) {
        return consumerRepository.save(consumer);
    }

    public Consumer findByPseudo(String pseudo) {
        return consumerRepository.getConsumersByPseudo(pseudo);
    }

    public ConsumerDTO getConsumerDTOByPseudo(String pseudo) {
        Consumer consumerByPseudo = consumerRepository.getConsumersByPseudo(pseudo);
        Consumer consumerByMail = consumerRepository.getConsumersByMail(pseudo);

        ConsumerDTO consumerDTO = new ConsumerDTO();

        if (consumerByPseudo == null && consumerByMail == null) {
            throw new RuntimeException("Consumer not found with pseudo " + pseudo);
        }else if(consumerByPseudo == null){
            consumerDTO.setPseudo(consumerByMail.getPseudo());
            consumerDTO.setMail(consumerByMail.getMail());
            consumerDTO.setPassword(consumerByMail.getPassword());
        }else{
            consumerDTO.setPseudo(consumerByPseudo.getPseudo());
            consumerDTO.setMail(consumerByPseudo.getMail());
            consumerDTO.setPassword(consumerByPseudo.getPassword());
        }

        return consumerDTO;
    }

    public List<Consumer> getAllConsumers() {
        return consumerRepository.findAll();
    }

    public Consumer getConsumerById(Integer id) {
        Optional<Consumer> consumer = consumerRepository.findById(id);
        return consumer.orElseThrow(() -> new RuntimeException("Consumer not found with id " + id));
    }

    public Consumer updateConsumer( Consumer consumerDetails) {
        Consumer updateConsumer = new Consumer();
//        updateConsumer.setId(consumerDetails.getId());
        updateConsumer.setPseudo(consumerDetails.getPseudo());
        updateConsumer.setPassword(consumerDetails.getPassword());
        updateConsumer.setMail(consumerDetails.getMail());
        Role role = new Role();
        role.setId(1);
        updateConsumer.setIdRole(role);
        System.out.println("------");
        System.out.println(updateConsumer.getId());

//        existingConsumer.setTitle(consumerDetails.getTitle());
            return consumerRepository.save(consumerDetails);


    }

    public void deleteConsumer(Integer id) {
        consumerRepository.deleteById(id);
    }
}
