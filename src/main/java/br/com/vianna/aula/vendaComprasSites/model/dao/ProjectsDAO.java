package br.com.vianna.aula.vendaComprasSites.model.dao;

import br.com.vianna.aula.vendaComprasSites.model.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectsDAO extends JpaRepository<Projects, Integer> {
    List<Projects> findByisOpenTrue();
}
