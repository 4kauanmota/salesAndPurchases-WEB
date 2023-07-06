package br.com.vianna.aula.vendaComprasSites.controller;

import br.com.vianna.aula.vendaComprasSites.model.Specs;
import br.com.vianna.aula.vendaComprasSites.service.SpecsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static br.com.vianna.aula.vendaComprasSites.controller.UserInformations.setInformations;

@Controller
@RequestMapping(path = {"/specs"})
public class SpecsController {

    @Autowired
    SpecsService ss;

    @GetMapping("")
    public String pageList(Model model, Authentication auth){
        setInformations(model, auth);

        List<Specs> allSpecs = ss.getAll();
        model.addAttribute("specs", allSpecs);

        return "specs/List";
    }

    @GetMapping("/register")
    public String pageRegister(Model model, Authentication auth){
        setInformations(model, auth);

        Specs spec = Specs.builder()
                .id(0)
                .name("")
                .projects(new ArrayList<>())
                .build();

        model.addAttribute("spec", spec);
        model.addAttribute("action", "Register");

        return "specs/Register";
    }

    @PostMapping("/save")
    public String pageSave(@ModelAttribute Specs spec, Model model, Authentication auth){
        setInformations(model, auth);

        ss.save(spec);

        return "redirect:/specs";
    }

    @PostMapping("/edit/{id}")
    public String pageEdit(@PathVariable Integer id, Model model, Authentication auth){
        setInformations(model, auth);

        Specs spec = ss.getOne(id);

        model.addAttribute("spec", spec);
        model.addAttribute("action", "Update");

        return "specs/Register";
    }

    @PostMapping("/remove/{id}")
    public String pageRemove(@PathVariable Integer id, Model model, Authentication auth){
        ss.deleteOne(id);

        return "redirect:/specs";
    }
}
