package friendsofmine;

import friendsofmine.domain.Activite;
import friendsofmine.domain.Utilisateur;
import friendsofmine.services.UtilisateurActiviteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@Transactional
public class UtilisateurActiviteServiceIntegrationTest {
/*
    @Autowired
    private UtilisateurActiviteService utilisateurActiviteService;

    private Activite activite;
    private Utilisateur utilisateur;

    @BeforeEach
    public void setUp() {

        // given: a valid instance of Utilisateur
        utilisateur = new Utilisateur("Yorke", "Thom", "thom@yorke.com", "M");
        // given: a valid instance of Activite
        activite = new Activite("Une activité", "Description de l'activité");

    }

    @Test
    public void testSaveValidActivite() {

        // given: a valid activite

        // when: saving the activite
        utilisateurActiviteService.save(activite);

        // then: the activite is saved with a generated id
        assertThat(activite.getId(), is(notNullValue()));
    }

    @Test
    public void testSaveNonValidActivite() {

        // given: a non valid activite
        activite.setTitre(null);

        // when saving the project
        utilisateurActiviteService.save(activite);

        // then: an exception is thrown when synchronizing the persistance context and the database
        Assertions.assertThrows(ConstraintViolationException.class,
                () -> { utilisateurActiviteService.getEntityManager().flush(); });
    }

    @Test
    public void testSaveValidUtilisateur() {

        // given: a valid utilisateur

        // when: saving the utilisateur
        utilisateurActiviteService.save(utilisateur);

        // then: the utilisateur is saved with a generated id
        assertThat(utilisateur.getId(), is(notNullValue()));
    }

    @Test
    public void testSaveNonValidUtilisateur() {

        // given: a non valid Utilisateur
        utilisateur.setNom(null);

        // when: saving the Utilisateur
        utilisateurActiviteService.save(utilisateur);

        // then: an exception is thrown when synchronizing the persistance context and the database
        Assertions.assertThrows(ConstraintViolationException.class,
                () -> { utilisateurActiviteService.getEntityManager().flush(); });
    }

    @Test
    public void testFindExistingActiviteById() {

        // given: a saved activite
        utilisateurActiviteService.save(activite);


        // when: an existing  activite is searched by id
        Activite fetchedActivite = utilisateurActiviteService.findActiviteById(activite.getId());

        // then: the fetched activite is correctly instanced
        assertThat(fetchedActivite.getTitre(), is(activite.getTitre()));
        assertThat(fetchedActivite.getDescriptif(), is(activite.getDescriptif()));

    }

    @Test
    public void testFindNonExistingActiviteById() {

        // when: a non existing  activite is searched by id
        Activite fetchedActivite = utilisateurActiviteService.findActiviteById(10L);

        // then: the fetched activite is null
        assertThat(fetchedActivite, is(nullValue()));

    }

    @Test
    public void testFindExistingUtilisateur() {
        // given: a saved utilisateur
        utilisateurActiviteService.save(utilisateur);

        // when: an existing  utilisateur is searched by id
        Utilisateur fetchedUtilisateur = utilisateurActiviteService.findUtilisateurById(utilisateur.getId());

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
*/
}
