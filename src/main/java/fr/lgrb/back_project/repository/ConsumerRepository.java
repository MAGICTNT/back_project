package fr.lgrb.back_project.repository;

import fr.lgrb.back_project.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {
    public Consumer getConsumersByPseudo(String consumer);
}
