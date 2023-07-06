package br.com.vianna.aula.vendaComprasSites.controller;

import br.com.vianna.aula.vendaComprasSites.Configuration.User.Login;
import br.com.vianna.aula.vendaComprasSites.model.Admins;
import br.com.vianna.aula.vendaComprasSites.model.Rules;
import br.com.vianna.aula.vendaComprasSites.service.AdminsService;
import br.com.vianna.aula.vendaComprasSites.service.RulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.vianna.aula.vendaComprasSites.controller.UserInformations.setInformations;

@Controller
@RequestMapping(path = {"/rules"})
public class RulesController {

    @Autowired
    RulesService rs;

    @Autowired
    AdminsService as;

    @GetMapping("")
    public String pageList(Model model, Authentication auth){
        setInformations(model, auth);

        List<Rules> allRules = rs.getAll();
        model.addAttribute("rules", allRules);

        return "rules/List";
    }

    @GetMapping("/register")
    public String pageRegister(Model model, Authentication auth){
        setInformations(model, auth);

        Rules rule = Rules.builder()
                .id(0)
                .title("")
                .description("")
                .build();

        model.addAttribute("rule", rule);
        model.addAttribute("action", "Register");

        return "rules/Register";
    }

    @PostMapping("/save")
    public String pageSave(@ModelAttribute Rules rule, Model model, Authentication auth){
        setInformations(model, auth);

        Admins admin = as.getOne(((Login)auth.getPrincipal()).getUser().getId());
        admin.setAddedRules(admin.getAddedRules() + 1);

        rs.save(rule);

        return "redirect:/rules";
    }

    @PostMapping("/edit/{id}")
    public String pageEdit(@PathVariable Integer id, Model model, Authentication auth){
        setInformations(model, auth);

        Rules rule = rs.getOne(id);

        model.addAttribute("rule", rule);
        model.addAttribute("action", "Update");

        return "rules/Register";
    }

    @PostMapping("/remove/{id}")
    public String pageRemove(@PathVariable Integer id, Model model, Authentication auth){
        rs.deleteOne(id);

        return "redirect:/rules";
    }
}
