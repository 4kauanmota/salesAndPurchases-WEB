package br.com.vianna.aula.vendaComprasSites.model.dao;

import br.com.vianna.aula.vendaComprasSites.model.Circulations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CirculationsDAO extends JpaRepository<Circulations, Integer> {
}
