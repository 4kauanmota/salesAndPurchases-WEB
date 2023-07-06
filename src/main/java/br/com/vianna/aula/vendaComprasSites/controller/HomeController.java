package br.com.vianna.aula.vendaComprasSites.controller;

import br.com.vianna.aula.vendaComprasSites.model.Clients;
import br.com.vianna.aula.vendaComprasSites.service.RulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;

import static br.com.vianna.aula.vendaComprasSites.controller.UserInformations.setInformations;

@Controller
@RequestMapping(path = {"/home", "/"})
public class HomeController {
    @Autowired
    private RulesService rules;

    @GetMapping("")
    public String pageHome(Model model, Authentication auth){
        setInformations(model, auth);
        return "Home";
    }

    @GetMapping("/help")
    public String pageHelp(Model model, Authentication auth){
        setInformations(model, auth);

        model.addAttribute("rules", rules.getAll());

        return "Help";
    }

    @GetMapping("/login")
    public String pageLogin(Model model, Authentication auth){
        setInformations(model, auth);
        return "user/Login";
    }

    @GetMapping("/register")
    public String pageRegister(Model model, Authentication auth){
        setInformations(model, auth);

        Clients c = Clients.builder()
                .id(0)
                .name("")
                .email("")
                .login("")
                .password("")
                .lastAcessDate(new Date())
                .projects(new ArrayList<>())
                .profilePicture("")
                .balance(0)
                .circulations(new ArrayList<>())
                .evaluations(new ArrayList<>())
                .build();

        model.addAttribute("client", c);

        return "user/Register";
    }
}
