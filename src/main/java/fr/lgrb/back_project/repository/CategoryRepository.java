package fr.lgrb.back_project.repository;

import fr.lgrb.back_project.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
