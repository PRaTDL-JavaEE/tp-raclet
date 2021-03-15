package friendsofmine;

import friendsofmine.domain.Activite;
import friendsofmine.domain.Utilisateur;
import friendsofmine.services.UtilisateurActiviteService;
import org.hamcrest.Matchers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.core.Is.is;

@SpringBootTest
@AutoConfigureMockMvc
public class UtilisateurControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UtilisateurActiviteService utilisateurActiviteService;

    private Utilisateur util, utilNonSauve;

    @BeforeEach
    public void setup() {
        util = utilisateurActiviteService.save(new Utilisateur("Doe", "John", "john@doe.com", "M"));
        utilNonSauve = new Utilisateur("Morrissey", "Steven Patrick", "momo@rrissey.co.uk", "M");
    }

    @Test
    public void testGetUtilisateurs() throws Exception{
        // given: un objet MockMvc qui simulate des échanges Mvc
        // when: on simule du requête HTTP de type GET vers "/utilisateurs"
        // then: la requête est acceptée (status OK) et la vue "utilisateurs" est rendue
        // then: la vue retournée comporte le code HTML "<h2>Liste des Utilisateurs</h2>"
        mockMvc.perform(get("/utilisateurs"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("utilisateurs"))
                .andExpect(content().string(Matchers.containsString("<h2>Liste des Utilisateurs</h2>")))
                .andDo(print());
    }

    @Test
    public void testReadUtilisateurIdValide() throws Exception{
        // given: un objet MockMvc qui simulate des échanges Mvc
        // when: on simule du requête HTTP de type GET vers "/utilisateur/{id}" avec id valide
        // then: la requête est acceptée (status OK) et la vue "utilisateurShow" est rendue
        // then: la vue retournée comporte les infos sur l'utilisateur correspondant à l'id de l'URL
        mockMvc.perform(get("/utilisateur/" + util.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("utilisateurShow"))
                .andExpect(content().string(Matchers.containsString(util.getNom())))
                .andExpect(content().string(Matchers.containsString(util.getPrenom())))
                .andExpect(content().string(Matchers.containsString(util.getEmail())))
                .andDo(print());
    }

    @Test
    public void testReadUtilisateurIdInvalide() throws Exception{
        // given: un objet MockMvc qui simulate des échanges Mvc
        // when: on simule du requête HTTP de type GET vers "/utilisateur/{id}" avec id invalide
        // then: la requête est acceptée (status OK) et la vue "error" est rendue
        mockMvc.perform(get("/utilisateur/" + Integer.MAX_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("error"))
                .andDo(print());
    }

    @Test
    public void testCreateUtilisateur() throws Exception{
        // given: un objet MockMvc qui simulate des échanges Mvc
        // when: on simule du requête HTTP de type GET vers "/utilisateur/new"
        // then: la requête est acceptée (status OK) et la vue "utilisateurForm" est rendue
        mockMvc.perform(get("/utilisateur/new"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("utilisateurForm"))
                .andDo(print());
    }

    @Test
    public void testCreateUtilisateurValide() throws Exception{
        // given: un objet MockMvc qui simulate des échanges Mvc
        // when: on simule du requête HTTP de type POST vers "/utilisateur/"
        // when: la requête comporte un Utilisateur valide
        // then: le status de la reqûete est FOUND
        // then: une redirection a lieu vers l'adresse /utilisateur/{id} où id est l'id de l'utilisateur créé
        mockMvc.perform(post("/utilisateur")
                .param("nom", utilNonSauve.getNom())
                .param("prenom", utilNonSauve.getPrenom())
                .param("email", utilNonSauve.getEmail())
                .param("sexe", utilNonSauve.getSexe()))
                .andExpect(status().isFound())
                .andExpect(redirectedUrlPattern("/utilisateur/*"))
                .andDo(print());
    }

    @Test
    public void testCreateUtilisateurNomInvalide() throws Exception{
        // given: un objet MockMvc qui simulate des échanges Mvc
        // when: on simule du requête HTTP de type POST vers "/utilisateur/"
        // when: la requête comporte un Utilisateur invalide
        // then: la requête est acceptée (status OK)
        // then: la vue "utilisateurForm" est rendue avec un message d'erreur de validation
        mockMvc.perform(post("/utilisateur")
                .param("nom", "")
                .param("prenom", utilNonSauve.getPrenom())
                .param("email", utilNonSauve.getEmail())
                .param("sexe", utilNonSauve.getSexe()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("utilisateurForm"))
                .andExpect(content().string(Matchers.containsString("size must be between 1 and")))
                .andDo(print());
    }

    @Test
    public void testCreateUtilisateurPrenomInvalide() throws Exception{
        // given: un objet MockMvc qui simulate des échanges Mvc
        // when: on simule du requête HTTP de type POST vers "/utilisateur/"
        // when: la requête comporte un Utilisateur invalide
        // then: la requête est acceptée (status OK)
        // then: la vue "utilisateurForm" est rendue avec un message d'erreur de validation
        mockMvc.perform(post("/utilisateur")
                .param("nom", utilNonSauve.getNom())
                .param("prenom", "")
                .param("email", utilNonSauve.getEmail())
                .param("sexe", utilNonSauve.getSexe()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("utilisateurForm"))
                .andExpect(content().string(Matchers.containsString("size must be between 1 and")))
                .andDo(print());
    }

    @Test
    public void testCreateUtilisateurEmailInvalide() throws Exception{
        // given: un objet MockMvc qui simulate des échanges Mvc
        // when: on simule du requête HTTP de type POST vers "/utilisateur/"
        // when: la requête comporte un Utilisateur invalide
        // then: la requête est acceptée (status OK)
        // then: la vue "utilisateurForm" est rendue avec un message d'erreur de validation
        mockMvc.perform(post("/utilisateur")
                .param("nom", utilNonSauve.getNom())
                .param("prenom", utilNonSauve.getPrenom())
                .param("email", "toto")
                .param("sexe", utilNonSauve.getSexe()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("utilisateurForm"))
                .andExpect(content().string(Matchers.containsString("must be a well-formed email address")))
                .andDo(print());
    }

    @Test
    public void testEditUtilisateurIdValide() throws Exception{
        // given: un objet MockMvc qui simulate des échanges Mvc
        // when: on simule du requête HTTP de type GET vers "/utilisateur/edit/{id}" avec un id valide
        // then: la requête est acceptée (status OK)
        // then: la vue "utilisateurForm" est les infos de l'utilisateur dont l'id est id
        Long savId = util.getId();
        mockMvc.perform(get("/utilisateur/edit/" + savId))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("utilisateurForm"))
                .andExpect(content().string(Matchers.containsString(util.getNom())))
                .andExpect(content().string(Matchers.containsString(util.getPrenom())))
                .andExpect(content().string(Matchers.containsString(util.getEmail())))
                .andExpect(model().attribute("utilisateur", hasProperty("id", is(savId))))
                .andDo(print());
    }

    @Test
    public void testEditUtilisateurIdInvalide() throws Exception{
        // given: un objet MockMvc qui simulate des échanges Mvc
        // when: on simule du requête HTTP de type GET vers "/utilisateur/edit/{id}" avec un id invalide
        // then: la requête est acceptée (status OK)
        // then: la vue "error" est rendue
        mockMvc.perform(get("/utilisateur/edit/" + (util.getId() + 1)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("error"))
                .andDo(print());
    }

    @Test
    public void testDeleteUtilisateur() throws Exception {
        // given: un objet MockMvc qui simulate des échanges Mvc
        // when: on simule du requête HTTP de type GET vers "/utilisateur/delete" avec un id valide
        // then: la requête est acceptée (status OK)
        // then: une redirection vers la requête GET vers "/utilisateurs" a lieu
        // then: le nombre d'utilisateurs en base a diminué de 1
        long count = utilisateurActiviteService.findAllUtilisateur().size();
        assertTrue(count > 0);
        mockMvc.perform(get("/utilisateur/delete/" + util.getId()))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/utilisateurs"))
                .andDo(print());
        assertEquals(count - 1, utilisateurActiviteService.findAllUtilisateur().size());
    }

    @Test
    public void testDeleteUtilisateurAvecActivite() throws Exception {
        // given: un objet MockMvc qui simulate des échanges Mvc
        // when: on simule du requête HTTP de type GET vers "/utilisateur/delete/{id}" où id est l'id d'un Utilisateur en base
        //       qui est responsable d'une activité
        // then: la requête est acceptée (status OK)
        // then: la vue "error" est rendue
        long count = utilisateurActiviteService.findAllUtilisateur().size();
        Activite act = new Activite("Football", "Le mardi soir", util);
        act = utilisateurActiviteService.save(act);
        util = act.getResponsable();
        assertTrue(util.getActivites().size() > 0);
        mockMvc.perform(get("/utilisateur/delete/" + util.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("error"))
                .andExpect(content().string(Matchers.containsString("L&#39;utilisateur est responsable d&#39;activités")))
                .andDo(print());
        assertEquals(count, utilisateurActiviteService.findAllUtilisateur().size());
    }


}
