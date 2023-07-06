package br.com.vianna.aula.vendaComprasSites.model.dao;

import br.com.vianna.aula.vendaComprasSites.model.Evaluations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationsDAO extends JpaRepository<Evaluations, Integer> {
}
