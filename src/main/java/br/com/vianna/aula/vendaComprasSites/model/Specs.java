package br.com.vianna.aula.vendaComprasSites.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Specs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 30, nullable = false)
    private String name;

    @ManyToMany(fetch=FetchType.EAGER)
    private List<Projects> projects;
}
