package br.com.vianna.aula.vendaComprasSites.controller;

import br.com.vianna.aula.vendaComprasSites.Configuration.User.Login;
import br.com.vianna.aula.vendaComprasSites.model.*;
import br.com.vianna.aula.vendaComprasSites.service.AdminsService;
import br.com.vianna.aula.vendaComprasSites.service.ClientService;
import br.com.vianna.aula.vendaComprasSites.service.ProjectsService;
import br.com.vianna.aula.vendaComprasSites.service.SpecsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static br.com.vianna.aula.vendaComprasSites.controller.UserInformations.setInformations;

@Controller
@RequestMapping(path = {"/project"})
public class ProjectController {
    @Autowired
    private ProjectsService ps;

    @Autowired
    private ClientService cs;

    @Autowired
    private AdminsService as;

    @Autowired
    private SpecsService ss;

    @GetMapping("/register")
    public String pageProjectRegister(Model model, Authentication auth){
        setInformations(model, auth);

        List<Specs> specs = ss.getAll();

        Projects project = Projects.builder()
                .id(0)
                .title("")
                .description("")
                .cost(0)
                .link("")
                .thumbnail("")
                .specs(new ArrayList<>())
                .lastUpdate(new Date())
                .isOpen(true)
                .seller(null)
                .circulations(null)
                .build();

        model.addAttribute("project", project);
        model.addAttribute("specs", specs);

        return "project/Register";
    }

    @PostMapping("/save")
    public String pageSave(@ModelAttribute Projects project, @RequestParam("specs") List<Integer> specs, Model model, Authentication auth){
        Clients seller = cs.getOne( ((Login) auth.getPrincipal()).getUser().getId() );

        List<Specs> usedSpecs = new ArrayList<>();

        for(Integer i : specs){
            usedSpecs.add(ss.getOne(i));
        }

        project.setLastUpdate(new Date());
        project.setSeller(seller);
        project.setSpecs(usedSpecs);
        project.setOpen(true);

        seller.getProjects().add(project);

        ps.save(project);

        return "redirect:/user/projects";
    }

    @PostMapping("/remove/{id}")
    public String pageRemove(@PathVariable Integer id, Model model, Authentication auth){
        Admins admin = as.getOne(((Login)auth.getPrincipal()).getUser().getId());
        ps.deleteOne(id, admin);

        return "redirect:/store";
    }

    @PostMapping("/buy/{id}")
    public String pageBuy(@PathVariable Integer id, Model model, Authentication auth){
        Clients client = cs.getOne(((Login)auth.getPrincipal()).getUser().getId());
        Projects project = ps.getOne(id);

        if(client.getBalance() - project.getCost() > 0){
            ps.setCirculation(project, client);

            return "redirect:/user/circulations";
        }
        else{
            return "redirect:/store";
        }
    }

    @PostMapping("/devolution/{id}")
    public String pageDevolution(@PathVariable Integer id, Model model, Authentication auth){
        Clients client = cs.getOne(((Login)auth.getPrincipal()).getUser().getId());
        Projects project = ps.getOne(id);

        if(project.getCirculations().getBuyer().getId() == client.getId()){
            ps.deleteCirculation(project, client);
        }

        return "redirect:/store";
    }
}
