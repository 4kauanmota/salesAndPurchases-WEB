package br.com.vianna.aula.vendaComprasSites.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@DiscriminatorValue("C")
public class Clients extends Users{
    @Column(nullable = false)
    private double balance;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Projects> projects;

    @OneToMany(fetch=FetchType.EAGER)
    private List<Circulations> circulations;

    @OneToMany(fetch=FetchType.EAGER)
    private List<Evaluations> evaluations;
}
