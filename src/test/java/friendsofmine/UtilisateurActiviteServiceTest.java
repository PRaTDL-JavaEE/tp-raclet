package friendsofmine;

import friendsofmine.domain.Activite;
import friendsofmine.domain.Utilisateur;
import friendsofmine.services.UtilisateurActiviteService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class UtilisateurActiviteServiceTest {

    private UtilisateurActiviteService utilisateurActiviteService;

    @MockBean
    private EntityManager entityManager;

    @MockBean
    private Utilisateur utilisateur;

    @MockBean
    private Activite activite;

    private Long anId = 1L;

    @BeforeEach
    public void setUp() throws Exception {
        utilisateurActiviteService = new UtilisateurActiviteService();
        utilisateurActiviteService.setEntityManager(entityManager);
    }

    @Test
    public void testEntityManagerPersistActiviteWhenActiviteIsSaved() {
        // when: trying to save a Activite
        utilisateurActiviteService.save(activite);

        // then: the persist method is invoke on the entity manager
        verify(utilisateurActiviteService.getEntityManager()).persist(activite);
    }

    @Test
    public void testEntityManagerPersistAUtilisateurWhenUtilisateurIsSaved() {
        // when: trying to save a Utilisateur
        utilisateurActiviteService.save(utilisateur);

        // then: the persist method is invoke on the entity manager
        verify(utilisateurActiviteService.getEntityManager()).persist(utilisateur);
    }

    @Test
    public void testEntityManagerFindActiviteWhenActiviteIsSearchedById() {

        // when: trying to find an Activite
        utilisateurActiviteService.findActiviteById(anId);

        // then: the find method is invoke on the entity manager
        verify(entityManager).find(Activite.class, anId);
    }

    @Test
    public void testEntityManagerFindUtilisateurWhenUtilisateurIsSearchedById() {

        // when: trying to find an Utilisateur
        utilisateurActiviteService.findUtilisateurById(anId);

        // then: the find method is invoke on the entity manager
        verify(entityManager).find(Utilisateur.class, anId);
    }

}
