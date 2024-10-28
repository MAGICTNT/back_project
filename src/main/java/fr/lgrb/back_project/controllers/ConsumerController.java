package fr.lgrb.back_project.controllers;

import fr.lgrb.back_project.dto.ConsumerDTO;
import fr.lgrb.back_project.dto.ConsumerReceivedDTO;
import fr.lgrb.back_project.dto.ConsumerSendDTO;
import fr.lgrb.back_project.entity.Consumer;
import fr.lgrb.back_project.entity.Role;
import fr.lgrb.back_project.error.ResourceNotFoundException;
import fr.lgrb.back_project.service.ConsumerService;
import fr.lgrb.back_project.utils.PassControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @PostMapping("/new")
    public Consumer creatConsumer(@RequestBody ConsumerDTO consumerDTO) throws Exception {
        System.out.println(consumerDTO);
        Consumer newConsumer = new Consumer();
        newConsumer.setPseudo(consumerDTO.getPseudo());
        newConsumer.setMail(consumerDTO.getMail());
        newConsumer.setPassword(consumerDTO.getPassword());
        Role role = new Role();
        role.setId(1);
        PassControl passControl = new PassControl();
        newConsumer.setIdRole(role);

        return consumerService.createConsumer(passControl.generatePassword(newConsumer));
    }

    @PostMapping("/login")
    public ConsumerSendDTO login(@RequestBody ConsumerReceivedDTO consumerDTO) throws Exception {
        ConsumerDTO consumer = consumerService.getConsumerDTOByPseudo(consumerDTO.getLogin());
        PassControl passControl = new PassControl();
        if (consumer != null && passControl.verifyPassword(consumerDTO.getPassword(), consumer.getPassword())) {
            return new ConsumerSendDTO(consumer.getPseudo(),consumer.getMail());
        } else {
            throw new RuntimeException("Invalid pseudo or password");
        }
    }

    @PutMapping("/update")
    public ConsumerDTO update(@RequestBody ConsumerDTO consumerDTO){
        System.out.println(consumerService.getConsumerDTOByPseudo(consumerDTO.getPseudo()));
        ConsumerDTO updateConsumer = consumerService.getConsumerDTOByPseudo(consumerDTO.getPseudo());
        if(updateConsumer == null){
            System.out.println("null");
            return updateConsumer;
        }
        System.out.println("not null");
        updateConsumer.setPassword(consumerDTO.getPassword());
        updateConsumer.setMail(consumerDTO.getMail());
        updateConsumer.setPassword(consumerDTO.getPassword());
//        consumerService.up
        return updateConsumer;
    }

    @DeleteMapping("/delete")
    public Map<String, Boolean> deleteConsumer(@RequestParam int id){
        Map<String, Boolean> response = new HashMap<>();

        try{
            consumerService.deleteConsumer(id);
            response.put("deleted", Boolean.TRUE);
            return response;
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Consumer not found with id: " + id);
        }
    }
}
