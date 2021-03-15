package friendsofmine.controllers;

import friendsofmine.domain.Utilisateur;
import friendsofmine.services.UtilisateurActiviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

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

    @GetMapping("/utilisateur/new")
    public String createUtilisateur(Model model){
        model.addAttribute("utilisateur", new Utilisateur());
        return "utilisateurForm";
    }

    @PostMapping(value = "/utilisateur")
    public String createOrUpdateUtilisateur(@Valid Utilisateur util,
                                            BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "utilisateurForm";
        }
        util = utilisateurActiviteService.save(util);
        return "redirect:/utilisateur/" + util.getId();
    }

}
