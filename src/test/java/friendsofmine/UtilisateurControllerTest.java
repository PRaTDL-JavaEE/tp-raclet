package friendsofmine;

import friendsofmine.domain.Utilisateur;
import friendsofmine.services.UtilisateurActiviteService;
import org.hamcrest.Matchers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UtilisateurControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UtilisateurActiviteService utilisateurActiviteService;

    private Utilisateur util;

    @BeforeEach
    public void setup() {
        util = utilisateurActiviteService.save(new Utilisateur("Doe", "John", "john@doe.com", "M"));
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

}
