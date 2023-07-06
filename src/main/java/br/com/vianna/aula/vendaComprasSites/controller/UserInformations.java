package br.com.vianna.aula.vendaComprasSites.controller;

import br.com.vianna.aula.vendaComprasSites.Configuration.User.Login;
import br.com.vianna.aula.vendaComprasSites.model.Admins;
import br.com.vianna.aula.vendaComprasSites.model.Clients;
import br.com.vianna.aula.vendaComprasSites.model.Evaluations;
import br.com.vianna.aula.vendaComprasSites.service.AdminsService;
import br.com.vianna.aula.vendaComprasSites.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import java.util.Date;

public class UserInformations {
    @Autowired
    private ClientService cs;

    @Autowired
    private AdminsService as;

    public static void setInformations(Model model, Authentication auth){
        try{
            model.addAttribute("userName", ((Login) auth.getPrincipal()).getUser().getName());
            model.addAttribute("userEmail", ((Login) auth.getPrincipal()).getUser().getEmail());
            model.addAttribute("userProfilePicture", ((Login) auth.getPrincipal()).getUser().getProfilePicture());

            if(((Login)auth.getPrincipal()).getUser() instanceof Clients){
                model.addAttribute("userBalance", "R$ " + ((Clients) ((Login) auth.getPrincipal()).getUser()).getBalance());

                double sum = 0, qtd = 0, evaluation = 0;
                for(Evaluations ev : ((Clients) ((Login) auth.getPrincipal()).getUser()).getEvaluations()){
                    sum += ev.getGrade();
                    qtd++;
                }
                if(sum/qtd >= 0 || sum/qtd <= 10){
                    evaluation = sum/qtd;
                }

                model.addAttribute("userEvaluation", "Evaluation: " + evaluation);
            }
            else{
                model.addAttribute("userBalance", "Contributions: " +   ((Admins) ((Login) auth.getPrincipal()).getUser()).getDeletedProjects() + " " + ((Admins) ((Login) auth.getPrincipal()).getUser()).getAddedRules() );
                model.addAttribute("userEvaluation", "AN: " + ((Admins) ((Login) auth.getPrincipal()).getUser()).getAdmNumber());
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            model.addAttribute("userName", "????????");
            model.addAttribute("userEmail", "????????");
            model.addAttribute("userBalance", "????????");
            model.addAttribute("userEvaluation", "????????");
            model.addAttribute("userProfilePicture", "https://upload.wikimedia.org/wikipedia/commons/2/20/Point_d_interrogation.jpg");
        }
    }
}
