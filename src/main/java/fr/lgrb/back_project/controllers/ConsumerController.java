package fr.lgrb.back_project.controllers;

import fr.lgrb.back_project.dto.*;
import fr.lgrb.back_project.entity.Consumer;
import fr.lgrb.back_project.entity.Role;
import fr.lgrb.back_project.error.ResourceNotFoundException;
import fr.lgrb.back_project.service.ConsumerService;
import fr.lgrb.back_project.utils.PassControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @PostMapping("/new")
    public ConsumerInscriptionDTO creatConsumer(@RequestBody ConsumerDTO consumerDTO) throws Exception {
        Consumer newConsumer = new Consumer();
        Consumer consumerByPseudo = consumerService.findByPseudo(consumerDTO.getPseudo());
        ConsumerInscriptionDTO consumerInscriptionDTO = new ConsumerInscriptionDTO();

        Map<Integer, String> pseudoControl = new HashMap<>();
        Map<Integer, String> mailControl = new HashMap<>();
        if (consumerByPseudo != null){
            pseudoControl.put(500, consumerDTO.getPseudo());
        }else {
            pseudoControl.put(200, consumerDTO.getPseudo());
        }

        Consumer consumerByMail = consumerService.findByMail(consumerDTO.getMail());
        if (consumerByMail != null){
            mailControl.put(500, consumerDTO.getMail());
        }else {
            mailControl.put(200, consumerDTO.getMail());
        }
        if(consumerByPseudo != null || consumerByMail != null){
            consumerInscriptionDTO.setMail(mailControl);
            consumerInscriptionDTO.setPseudo(pseudoControl);

            consumerInscriptionDTO.setRole("admin");
            return consumerInscriptionDTO;
        }
        newConsumer.setPseudo(consumerDTO.getPseudo());
        newConsumer.setMail(consumerDTO.getMail());
        newConsumer.setPassword(consumerDTO.getPassword());
        Role role = new Role();
        role.setId(1);
        PassControl passControl = new PassControl();
        newConsumer.setIdRole(role);
        pseudoControl.put(200, consumerDTO.getPseudo());

        consumerService.createConsumer(passControl.generatePassword(newConsumer));
        consumerInscriptionDTO.setMail(mailControl);
        consumerInscriptionDTO.setPseudo(pseudoControl);
        consumerInscriptionDTO.setRole("admin");
        return consumerInscriptionDTO;
    }

    @PostMapping("/login")
    public ConsumerSendDTO login(@RequestBody ConsumerReceivedDTO consumerDTO) throws Exception {
        ConsumerDTO consumer = consumerService.getConsumerDTOByPseudo(consumerDTO.getLogin());
        PassControl passControl = new PassControl();
        if (consumer != null && passControl.verifyPassword(consumerDTO.getPassword(), consumer.getPassword())) {
            return new ConsumerSendDTO(consumer.getPseudo(),consumer.getMail(), consumer.getRole());
        } else {
            throw new RuntimeException("Invalid pseudo or password");
        }
    }

    @PutMapping("/update")
    public Map<Integer, String> update(@RequestBody ConsumerDTO consumerDTO) throws Exception {
//    public Consumer update(@RequestBody ConsumerDTO consumerDTO) throws Exception {
        Map<Integer, String> response = new HashMap<>();

        Consumer upConsumer = consumerService.findByPseudo(consumerDTO.getPseudo());

        if(upConsumer == null){
            System.out.println("null");
            response.put(404, "Not Found");
            return response;
        }

        PassControl passControl = new PassControl();

        upConsumer.setMail(consumerDTO.getMail());
        if(!Objects.equals(consumerDTO.getPassword(), "")){
            upConsumer.setPassword(consumerDTO.getPassword());
            upConsumer = passControl.generatePassword(upConsumer);
        }
        upConsumer.setPseudo(consumerDTO.getPseudo());

        Role role = new Role();
        switch (consumerDTO.getRole()){
            case "admin" :{
                role.setId(1);
                break;
            }
            default: {
                role.setId(2);
                break;
            }
        }
        upConsumer.setIdRole(role);
        consumerService.updateConsumer(upConsumer);
        response.put(200, "update");
        return response;
    }
    @PutMapping("/update/mail")
    public Map<Integer, String> updateMail(@RequestBody ConsumerDTO consumerDTO) throws Exception {
        Map<Integer, String> response = new HashMap<>();

        Consumer upConsumer = consumerService.findByPseudo(consumerDTO.getPseudo());

        if(upConsumer == null){
            response.put(404, "Consumer Not Found");
            return response;
        }
        PassControl passControl = new PassControl();
        if(!passControl.verifyPassword(consumerDTO.getPassword(), upConsumer.getPassword())){
            response.put(401, "password Not Good");
            return response;
        }

        List<Consumer> listConsumerMail = consumerService.findAllByMail(consumerDTO.getMail());
        if(!listConsumerMail.isEmpty()){
            response.put(500, "Mail Found");
            return response;
        }


        upConsumer.setMail(consumerDTO.getMail());

        Role role = new Role();
        role.setId(upConsumer.getIdRole().getId());
        upConsumer.setIdRole(role);
        consumerService.updateConsumer(upConsumer);
        response.put(200, "update");
        return response;
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
