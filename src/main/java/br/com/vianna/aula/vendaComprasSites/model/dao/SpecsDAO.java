package br.com.vianna.aula.vendaComprasSites.model.dao;

import br.com.vianna.aula.vendaComprasSites.model.Specs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecsDAO extends JpaRepository<Specs, Integer> {
}