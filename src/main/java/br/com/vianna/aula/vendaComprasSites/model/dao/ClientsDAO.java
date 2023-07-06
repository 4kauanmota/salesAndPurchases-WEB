package br.com.vianna.aula.vendaComprasSites.model.dao;

import br.com.vianna.aula.vendaComprasSites.model.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsDAO extends JpaRepository<Clients, Integer> {
    public Clients findByName(String name);
    public Clients findByLogin(String login);
    public Clients findByNameAndEmail(String name, String email);
    public Clients findByLoginAndPassword(String login, String password);
}
