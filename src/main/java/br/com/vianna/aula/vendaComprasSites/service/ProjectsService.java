package br.com.vianna.aula.vendaComprasSites.service;

import br.com.vianna.aula.vendaComprasSites.Configuration.User.Login;
import br.com.vianna.aula.vendaComprasSites.model.*;
import br.com.vianna.aula.vendaComprasSites.model.dao.AdminsDAO;
import br.com.vianna.aula.vendaComprasSites.model.dao.CirculationsDAO;
import br.com.vianna.aula.vendaComprasSites.model.dao.ClientsDAO;
import br.com.vianna.aula.vendaComprasSites.model.dao.ProjectsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class ProjectsService {
    @Autowired
    ProjectsDAO projects;

    @Autowired
    CirculationsDAO circulations;

    @Autowired
    ClientsDAO clients;

    @Autowired
    AdminsDAO admins;

    public List<Projects> getAll(){
        return projects.findAll();
    }

    public List<Projects> getByOpen(){
        return projects.findByisOpenTrue();
    }

    public Projects getOne(int id){
        return projects.findById(id).get();
    }

    public void deleteOne(int id, Admins admin){
        Projects project = projects.getById(id);

        for (Specs spec : project.getSpecs()){
         spec.getProjects().remove(project);
        }

        project.setSpecs(null);
        project.getSeller().getProjects().remove(project);
        project.setSeller(null);
        projects.save(project);

        projects.deleteById(id);

        admin.setDeletedProjects(admin.getDeletedProjects() + 1);
        admins.save(admin);
    }

    public void deleteCirculation(Projects project, Clients client){
        Circulations circulation = circulations.getOne(project.getCirculations().getId());

        project.setCirculations(null);
        project.setOpen(true);
        client.getCirculations().remove(circulation);
        client.setBalance(client.getBalance() + project.getCost());
        project.getSeller().setBalance(project.getSeller().getBalance() - project.getCost());

        circulations.deleteById(circulation.getId());
        projects.save(project);
    }

    public void setCirculation(Projects project, Clients client){
        project.setOpen(false);

        Circulations circulation = Circulations.builder()
                .buyer(client)
                .dateOfPurchase(new Date())
                .project(project)
                .build();

        project.setCirculations(circulation);

        client.getCirculations().add(circulation);
        client.setBalance(client.getBalance() - project.getCost());
        project.getSeller().setBalance(project.getSeller().getBalance() + project.getCost());

        circulations.save(circulation);
    }

    @Transactional
    public void save(Projects p){
        projects.save(p);
    }
}
