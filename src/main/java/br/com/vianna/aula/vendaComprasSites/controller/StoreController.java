package br.com.vianna.aula.vendaComprasSites.controller;

import br.com.vianna.aula.vendaComprasSites.Configuration.User.Login;
import br.com.vianna.aula.vendaComprasSites.model.*;
import br.com.vianna.aula.vendaComprasSites.service.ClientService;
import br.com.vianna.aula.vendaComprasSites.service.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.List;

import static br.com.vianna.aula.vendaComprasSites.controller.UserInformations.setInformations;

@Controller
@RequestMapping(path = {"/store"})
public class StoreController {
    @Autowired
    private ClientService cs;

    @Autowired
    private ProjectsService ps;

    @GetMapping("")
    public String pageStore(Model model, Authentication auth){
        setInformations(model, auth);

        List<Projects> allProjects = ps.getByOpen();
        model.addAttribute("projects", allProjects);

        return "Store";
    }

    @GetMapping("/{id}")
    public String pageStoreDetails(@PathVariable Integer id, Model model, Authentication auth){
        setInformations(model, auth);

        Users user = ((Login)auth.getPrincipal()).getUser();

        Projects project = ps.getOne(id);

        if(project.getCirculations() == null || project.getSeller().getId() == user.getId() || project.getCirculations().getBuyer().getId() == user.getId()){
            Clients seller = project.getSeller();

            double sum = 0, qtd = 0, evaluation = 0;
            for(Evaluations ev : seller.getEvaluations()){
                sum += ev.getGrade();
                qtd++;
            }
            if(sum/qtd >= 0 || sum/qtd <= 10){
                evaluation = sum/qtd;
            }


            SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
            String projectLastUpdate = DateFor.format(project.getLastUpdate());
            String userLastUpdate = DateFor.format(seller.getLastAcessDate());

            model.addAttribute("project", project);
            model.addAttribute("projectLastUpdate", projectLastUpdate);
            model.addAttribute("seller", seller);
            model.addAttribute("evaluation", "Evaluation: " + evaluation);
            model.addAttribute("userLastUpdate", "Last seen on: " + userLastUpdate);
            if(user instanceof Admins){
                model.addAttribute("user", "admin");
            }
            else{
                model.addAttribute("user", "client");
            }
            if(project.getCirculations() != null){
                if (project.getCirculations().getBuyer().getId() == user.getId())
                    model.addAttribute("isOwner", "true");
                else
                    model.addAttribute("isOwner", "false");
            }
            else{
                model.addAttribute("isOwner", "false");
            }

            return "store/Project";
        }
        else{
            return "redirect:/store";
        }
    }
}
