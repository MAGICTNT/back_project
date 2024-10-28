package fr.lgrb.back_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "instruction")
public class Instruction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('instruction_id_instruction_seq')")
    @Column(name = "id_instruction", nullable = false)
    private Integer id;

    @Column(name = "description", nullable = false, length = Integer.MAX_VALUE)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_recipe", nullable = false)
    private Recipe idRecipe;

}