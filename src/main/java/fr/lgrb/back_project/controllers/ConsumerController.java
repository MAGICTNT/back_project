package fr.lgrb.back_project.controllers;

import fr.lgrb.back_project.dto.ConsumerDTO;
import fr.lgrb.back_project.entity.Consumer;
import fr.lgrb.back_project.entity.Role;
import fr.lgrb.back_project.service.ConsumerService;
import fr.lgrb.back_project.utils.PassControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
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
    public ConsumerDTO login(@RequestBody ConsumerDTO consumerDTO) throws Exception {
        ConsumerDTO consumer = consumerService.getConsumerDTOByPseudo(consumerDTO.getPseudo());
        PassControl passControl = new PassControl();
        if (consumer != null && passControl.verifyPassword(consumerDTO.getPassword(), consumer.getPassword())) {
            consumer.setPassword("");
            return consumer;
        } else {
            throw new RuntimeException("Invalid pseudo or password");
        }
    }
}
