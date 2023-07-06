package br.com.vianna.aula.vendaComprasSites.model.dao;

import br.com.vianna.aula.vendaComprasSites.model.Rules;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RulesDAO extends JpaRepository<Rules, Integer> {
}
