package fr.lgrb.back_project.service;

import fr.lgrb.back_project.entity.Liked;
import fr.lgrb.back_project.entity.LikedId;
import fr.lgrb.back_project.repository.LikedRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikedService {
    private final LikedRepository likedRepository;

    public LikedService(LikedRepository likedRepository) {
        this.likedRepository = likedRepository;
    }

    public List<Liked> findAll() {
        return likedRepository.findAll();
    }

    public Optional<Liked> findById(LikedId id) {
        return likedRepository.findById(id);
    }

    public List<Liked> findByConsumerId(int id){
        return likedRepository.findAllByIdConsumer_Id(id);
    }

    public Liked save(Liked liked) {
        return likedRepository.save(liked);
    }

    public void deleteById(LikedId id) {
        likedRepository.deleteById(id);
    }
}
