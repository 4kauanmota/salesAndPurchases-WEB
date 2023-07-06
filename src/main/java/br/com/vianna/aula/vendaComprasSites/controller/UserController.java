package br.com.vianna.aula.vendaComprasSites.controller;

import br.com.vianna.aula.vendaComprasSites.Configuration.User.Login;
import br.com.vianna.aula.vendaComprasSites.model.Circulations;
import br.com.vianna.aula.vendaComprasSites.model.Clients;
import br.com.vianna.aula.vendaComprasSites.model.Projects;
import br.com.vianna.aula.vendaComprasSites.service.ClientService;
import br.com.vianna.aula.vendaComprasSites.service.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static br.com.vianna.aula.vendaComprasSites.controller.UserInformations.setInformations;

@Controller
@RequestMapping(path = {"/user"})
public class UserController {
    @Autowired
    private ClientService cs;

    @Autowired
    private ProjectsService ps;

    @Autowired
    PasswordEncoder password;

    @PostMapping("/save")
    public String pageSave(@ModelAttribute Clients client, Model model, Authentication auth){
        client.setPassword(password.encode(client.getPassword()));
        client.setLastAcessDate(new Date());
        client.setBalance(1000);
        cs.save(client);
        return "redirect:/login";
    }

    @GetMapping("/projects")
    public String pageProjects(Model model, Authentication auth){
        setInformations(model, auth);

        Clients user = cs.getOne( ((Login) auth.getPrincipal()).getUser().getId() );

        List<Projects> allProjects = user.getProjects();
        model.addAttribute("projects", allProjects);

        return "user/Projects";
    }

    @GetMapping("/circulations")
    public String pageCirculations(Model model, Authentication auth){
        setInformations(model, auth);

        Clients user = cs.getOne( ((Login) auth.getPrincipal()).getUser().getId() );

        List<Circulations> allCirculations = user.getCirculations();
        model.addAttribute("circulations", allCirculations);

        return "user/Circulations";
    }
}
