package br.com.vianna.aula.vendaComprasSites.service;

import br.com.vianna.aula.vendaComprasSites.model.Rules;
import br.com.vianna.aula.vendaComprasSites.model.dao.RulesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RulesService {

    @Autowired
    RulesDAO rules;


    public List<Rules> getAll(){
        return rules.findAll();
    }

    public Rules getOne(int id){
        return rules.findById(id).get();
    }

    public void deleteOne(int id){
        rules.deleteById(id);
    }

    @Transactional
    public void save(Rules rule){
        rules.save(rule);
    }
}
