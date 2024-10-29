package fr.lgrb.back_project.repository;


import fr.lgrb.back_project.entity.Liked;
import fr.lgrb.back_project.entity.LikedId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikedRepository extends JpaRepository<Liked, LikedId> {
    List<Liked> findAllByIdConsumer_Id(int id);
}
