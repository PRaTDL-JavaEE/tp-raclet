package friendsofmine;

import friendsofmine.domain.Activite;
import friendsofmine.domain.Utilisateur;
import friendsofmine.services.UtilisateurActiviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataLoader {

    private UtilisateurActiviteService utilisateurActiviteService;
    private Utilisateur leon, arnaude, julien, yseult;
    private Activite petanque, sieste, guitare, footing, cardio, sophrologie, jogging;

    @Autowired
    public DataLoader(UtilisateurActiviteService utilisateurActiviteService) {
        this.utilisateurActiviteService = utilisateurActiviteService;
    }

    public void initUtilisateurs() {
        leon = new Utilisateur("Ferland", "Léon", "gb@gmail.com", "M");
        arnaude = new Utilisateur("Bilodeau", "Arnaude", "arnaude@yahoo.com", "F");
        julien = new Utilisateur("Breton", "Julien", "jb@hotmail.fr", "M");
        yseult = new Utilisateur("Baril", "Yseult", "yseult@gmail.com", "F");
    }

    public void initAndSaveActivite() {
        petanque = utilisateurActiviteService.save(new Activite("Pétanque", "Seulement à partir de mai", yseult));
        yseult = petanque.getResponsable();
        sieste = utilisateurActiviteService.save(new Activite("Sieste", "Tous les jours, dans la salle de repos", julien));
        julien = sieste.getResponsable();
        guitare = utilisateurActiviteService.save(new Activite("Guitare", "Matériel non fourni", leon));
        leon = guitare.getResponsable();
        footing = utilisateurActiviteService.save(new Activite("Footing", "Tous les midis", arnaude));
        arnaude = footing.getResponsable();
        cardio = utilisateurActiviteService.save(new Activite("Cardio", "Tous les samedis", leon));
        leon = cardio.getResponsable();
        sophrologie = utilisateurActiviteService.save(new Activite("Sophrologie", "En salle B-123", leon));
        leon = sophrologie.getResponsable();
        jogging = utilisateurActiviteService.save(new Activite("Jogging", "Tous les midis", julien));
        julien = jogging.getResponsable();

    }

    @PostConstruct
    public void init() {
        try {
            initUtilisateurs();
            initAndSaveActivite();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Utilisateur getLeon() {
        return leon;
    }

    public Utilisateur getArnaude() {
        return arnaude;
    }

    public Utilisateur getJulien() {
        return julien;
    }

    public Utilisateur getYseult() {
        return yseult;
    }

    public Activite getPetanque() {
        return petanque;
    }

    public Activite getSieste() {
        return sieste;
    }

    public Activite getGuitare() {
        return guitare;
    }

    public Activite getFooting() {
        return footing;
    }

    public Activite getCardio() {
        return cardio;
    }

    public Activite getSophrologie() {
        return sophrologie;
    }
}
