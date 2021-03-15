package friendsofmine.controllers;

import friendsofmine.domain.Utilisateur;
import friendsofmine.services.UtilisateurActiviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UtilisateurController {

    @Autowired
    private UtilisateurActiviteService utilisateurActiviteService;

    @GetMapping("/utilisateurs")
    public String list(Model model) {
        model.addAttribute("utilisateurs", utilisateurActiviteService.findAllUtilisateur());
        return "utilisateurs";
    }

    @GetMapping("utilisateur/{id}")
    public String showUtilisateur(@PathVariable Long id, Model model){
        Utilisateur util = utilisateurActiviteService.findUtilisateurById(id);
        if (util == null) {
            model.addAttribute("customMessage", "Impossible. Id non valide");
            return "error";
        }
        model.addAttribute("utilisateur", util);
        return "utilisateurShow";
    }

}
