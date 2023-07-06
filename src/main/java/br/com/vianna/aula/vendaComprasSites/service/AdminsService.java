package br.com.vianna.aula.vendaComprasSites.service;

import br.com.vianna.aula.vendaComprasSites.model.Admins;
import br.com.vianna.aula.vendaComprasSites.model.Clients;
import br.com.vianna.aula.vendaComprasSites.model.dao.AdminsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AdminsService {
    @Autowired
    AdminsDAO admin;

    public Admins getOne(int id){
        return admin.findById(id).get();
    }
}
