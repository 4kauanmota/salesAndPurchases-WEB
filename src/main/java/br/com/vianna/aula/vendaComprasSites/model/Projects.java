package br.com.vianna.aula.vendaComprasSites.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 30, nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double cost;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private String thumbnail;

    @ManyToMany(fetch=FetchType.EAGER)
    private List<Specs> specs;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @Column(nullable = false)
    private boolean isOpen;

    @ManyToOne
    private Clients seller;

    @OneToOne
    private Circulations circulations;
}
