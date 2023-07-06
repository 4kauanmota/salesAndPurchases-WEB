package br.com.vianna.aula.vendaComprasSites.service;

import br.com.vianna.aula.vendaComprasSites.model.Clients;
import br.com.vianna.aula.vendaComprasSites.model.dao.ClientsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ClientService {
    @Autowired
    ClientsDAO client;

    public Clients getByLogin(String login){
        return client.findByLogin(login);
    }

    public Clients getOne(int id){
        return client.findById(id).get();
    }

    @Transactional
    public void save(Clients c){
        client.save(c);
    }
}
