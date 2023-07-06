package br.com.vianna.aula.vendaComprasSites.service;

import br.com.vianna.aula.vendaComprasSites.model.Specs;
import br.com.vianna.aula.vendaComprasSites.model.dao.SpecsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SpecsService {
    @Autowired
    SpecsDAO specs;

    public Specs getOne(int id){
        return specs.findById(id).get();
    }

    public List<Specs> getAll(){
        return specs.findAll();
    }

    public void deleteOne(int id){
        specs.deleteById(id);
    }

    @Transactional
    public void save(Specs p){
        specs.save(p);
    }
}
