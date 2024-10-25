package fr.lgrb.back_project.service;

import fr.lgrb.back_project.dto.ConsumerDTO;
import fr.lgrb.back_project.entity.Consumer;
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
        Consumer consumer = consumerRepository.getConsumersByPseudo(pseudo);
        System.out.println("consumer : " + consumer.getPassword());
        if (consumer == null) {
            throw new RuntimeException("Consumer not found with pseudo " + pseudo);
        }

        // Mapper le Consumer vers ConsumerDTO
        ConsumerDTO consumerDTO = new ConsumerDTO();
        consumerDTO.setPseudo(consumer.getPseudo());
        consumerDTO.setMail(consumer.getMail());
        consumerDTO.setPassword(consumer.getPassword());
        // Ajoutez d'autres propriétés si nécessaire

        return consumerDTO;
    }

    public List<Consumer> getAllConsumers() {
        return consumerRepository.findAll();
    }

    public Consumer getConsumerById(Integer id) {
        Optional<Consumer> consumer = consumerRepository.findById(id);
        return consumer.orElseThrow(() -> new RuntimeException("Consumer not found with id " + id));
    }

//    public Consumer updateConsumer(Integer id, Consumer consumerDetails) {
//        Consumer existingConsumer = getConsumerById(id);
////        existingConsumer.setTitle(consumerDetails.getTitle());
//        return consumerRepository.save(existingConsumer);
//    }

    public void deleteConsumer(Integer id) {
        consumerRepository.deleteById(id);
    }
}
