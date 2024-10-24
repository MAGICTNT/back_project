package fr.lgrb.back_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "consumer")
public class Consumer {
    @Id
    @ColumnDefault("nextval('consumer_id_consumer_seq')")
    @Column(name = "id_consumer", nullable = false)
    private Integer id;

    @Column(name = "pseudo", nullable = false)
    private String pseudo;

    @Column(name = "mail", nullable = false)
    private String mail;

    @Column(name = "password", nullable = false, length = Integer.MAX_VALUE)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_role", nullable = false)
    private Role idRole;

}