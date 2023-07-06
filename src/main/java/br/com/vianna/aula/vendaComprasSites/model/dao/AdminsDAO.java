package br.com.vianna.aula.vendaComprasSites.model.dao;

import br.com.vianna.aula.vendaComprasSites.model.Admins;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminsDAO extends JpaRepository<Admins, Integer> {
    public Admins findByName(String name);
    public Admins findByLogin(String login);
    public Admins findByNameAndEmail(String name, String email);
    public Admins findByLoginAndPassword(String login, String password);
}
