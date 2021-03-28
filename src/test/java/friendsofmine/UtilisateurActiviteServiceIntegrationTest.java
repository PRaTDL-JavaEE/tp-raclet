package friendsofmine;

import friendsofmine.domain.Activite;
import friendsofmine.domain.Utilisateur;
import friendsofmine.services.UtilisateurActiviteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@Transactional
public class UtilisateurActiviteServiceIntegrationTest {

    @Autowired
    private UtilisateurActiviteService utilisateurActiviteService;

    private Activite activite;
    private Utilisateur utilisateur;

    @BeforeEach
    public void setUp() {

        // given: a valid Utilisateur
        utilisateur = new Utilisateur("Martin","Jeanne","jeanne@martin.fr","F");

        // given: a valid activite
        activite = new Activite("Yoyo",
                "Ca monte et ça descend",
                utilisateur);

    }

    @Test
    public void testSaveValidActiviteWithNewUtilisateur() {

        // given: a valid activite

        // when saving the activite
        Activite managedActivite = utilisateurActiviteService.save(activite);

        // then: the activite is saved with a generated id
        assertThat(managedActivite.getId(), is(notNullValue()));

        // then: its utilisateur is saved too
        assertThat(managedActivite.getResponsable().getId(), is(notNullValue()));

        // then: the utilisateur has the activite referenced in its collection of activites
        assertThat(managedActivite.getResponsable().getActivites(), hasItem(managedActivite));

    }

    @Test
    public void testSaveValidActiviteWithAlreadySavedUtilisateur() {

        // given: a valid activite and an already saved utilisateur
        utilisateurActiviteService.save(utilisateur);

        // when saving the activite
        Activite managedActivite = utilisateurActiviteService.save(activite);

        // then: the activite is saved with a generated id
        assertThat(managedActivite.getId(), is(notNullValue()));

        // then: the utilisateur has the activite referenced in its collection of activites
        Utilisateur managedUtilisateur = managedActivite.getResponsable();
        assertThat(managedUtilisateur.getActivites(), hasItem(managedActivite));

    }

    @Test
    public void testSaveValidUtilisateur() {

        // given: a valid Utilisateur

        // when saving the utilisateur
        Utilisateur managedUtilisateur = utilisateurActiviteService.save(utilisateur);

        // then: the utilisateur is saved with a generated id
        assertThat(managedUtilisateur.getId(), is(notNullValue()));
    }

    @Test
    public void testFindExistingActiviteById() {

        // given: a saved activite
        activite = utilisateurActiviteService.save(activite);


        // when an existing  activite is searched by id
        Activite fetchedActivite = utilisateurActiviteService.findActiviteById(activite.getId());

        // then: the fetched activite is correctly instanced
        assertThat(fetchedActivite.getTitre(), is(activite.getTitre()));
        assertThat(fetchedActivite.getDescriptif(), is(activite.getDescriptif()));

    }

    @Test
    public void testFindNonExistingActiviteById() {

        // when: a non existing  activite is searched by id
        Activite fetchedActivite = utilisateurActiviteService.findActiviteById(100L);

        // then: the fetched activite is null
        assertThat(fetchedActivite, is(nullValue()));

    }

    @Test
    public void testFindExistingUtilisateur() {
        // given: a saved Utilisateur
        Utilisateur managedUtilisateur = utilisateurActiviteService.save(utilisateur);

        // when: an existing  utilisateur is searched by id
        Utilisateur fetchedUtilisateur = utilisateurActiviteService.findUtilisateurById(managedUtilisateur.getId());

        // then: the fetched utilisateur is correctly instanced
        assertThat(fetchedUtilisateur.getNom(), is(utilisateur.getNom()));
        assertThat(fetchedUtilisateur.getPrenom(), is(utilisateur.getPrenom()));
        assertThat(fetchedUtilisateur.getEmail(), is(utilisateur.getEmail()));
        assertThat(fetchedUtilisateur.getSexe(), is(utilisateur.getSexe()));

    }

    @Test
    public void testFindNonExistingUtilisateurById() {

        // when: a non existing  activite is searched by id
        Utilisateur fetchedUtilisateur = utilisateurActiviteService.findUtilisateurById(10L);

        // then: the fetched activite is null
        assertThat(fetchedUtilisateur, is(nullValue()));
    }

    /*
    @Test
    public void testFindAllUtilisateur() {

        // given: three persisted Utilisateur
        utilisateurActiviteService.save(new Utilisateur("Pierre", "Paule", "paule@yeah.co.uk", "F"));
        utilisateurActiviteService.save(new Utilisateur("Casablancas", "Julian", "jc@thestrokes.com", "M"));
        utilisateurActiviteService.save(utilisateur);

        // when: searching for all Utilisateur
        List<Utilisateur> utilisateurs = utilisateurActiviteService.findAllUtilisateur();

        // then: all Utilisateur are fetched
        assertThat(utilisateurs.size(), is(7)); // 3 + 4 from DataLoader

        // then: Utilisateur are sorted by name
        assertThat(utilisateurs.get(0).getNom(), is("Baril"));
        assertThat(utilisateurs.get(1).getNom(), is("Bilodeau"));
        assertThat(utilisateurs.get(2).getNom(), is("Breton"));
        assertThat(utilisateurs.get(3).getNom(), is("Casablancas"));
    }

    @Test
    public void testFindAllUtilisateurFromDataLoader() {
        // given: the initialization of DataLoader

        // when: searching for all Utilisateur
        List<Utilisateur> utilisateurs = utilisateurActiviteService.findAllUtilisateur();

        // then: 4 Utilisateur are fetched
        assertThat(utilisateurs.size(), is(4));

        // then: Utilisateur are sorted by title
        assertThat(utilisateurs.get(0).getNom(), is("Baril"));
        assertThat(utilisateurs.get(1).getNom(), is("Bilodeau"));
        assertThat(utilisateurs.get(2).getNom(), is("Breton"));
        assertThat(utilisateurs.get(3).getNom(), is("Ferland"));

    }
*/
}
