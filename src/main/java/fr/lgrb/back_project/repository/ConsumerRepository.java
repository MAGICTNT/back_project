package fr.lgrb.back_project.repository;

import fr.lgrb.back_project.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {
    public Consumer getConsumersByPseudo(String pseudo);
    Consumer getConsumersByMail(String mail);
    List<Consumer> findAllByMail(String mail);

}
