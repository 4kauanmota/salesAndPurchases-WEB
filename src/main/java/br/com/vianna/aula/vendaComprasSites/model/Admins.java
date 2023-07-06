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
@DiscriminatorValue("A")
public class Admins extends Users{
    @Column(nullable = false)
    private int admNumber;

    @Column(nullable = false)
    private int deletedProjects;

    @Column(nullable = false)
    private int addedRules;
}