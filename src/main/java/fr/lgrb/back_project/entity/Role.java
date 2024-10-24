package fr.lgrb.back_project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {
    @Id
    @ColumnDefault("nextval('role_id_role_seq')")
    @Column(name = "id_role", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

}