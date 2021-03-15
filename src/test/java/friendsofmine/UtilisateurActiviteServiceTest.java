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

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class UtilisateurActiviteServiceTest {

    private UtilisateurActiviteService utilisateurActiviteService;

    @MockBean
    private EntityManager entityManager;

    @MockBean(name = "activite")
    private Activite activite;

    @MockBean(name = "managedActivite")
    private Activite managedActivite;

    @MockBean
    private Utilisateur utilisateur;

    private Long anId = 1L;

    @BeforeEach
    public void setUp() throws Exception {
        utilisateurActiviteService = new UtilisateurActiviteService();
        utilisateurActiviteService.setEntityManager(entityManager);
        given(this.entityManager.merge(activite)).willReturn(managedActivite);
        given(this.activite.getResponsable()).willReturn(utilisateur);
        given(this.managedActivite.getResponsable()).willReturn(utilisateur);
    }

    @Test
    public void testEntityManagerPersistAnActiviteWhenActiviteIsSaved() {

        // when: trying to save a activite
        utilisateurActiviteService.save(activite);

        // then: the merge method is invoke on the entity manager
        verify(utilisateurActiviteService.getEntityManager()).merge(activite);
        // then: the merge method is invoke on the entity manager
        verify(entityManager).merge(utilisateur);
    }

    @Test
    public void testEntityManagerPersistAnUtilisateurWhenUtilisateurIsSaved() {
        // when: trying to save an utilisateur
        utilisateurActiviteService.save(utilisateur);

        // then: the merge method is invoke on the entity manager
        verify(entityManager).merge(utilisateur);
    }

    @Test
    public void testEntityManagerFindAActiviteWhenActiviteIsSearchedById() {

        // when: trying to save the activite
        utilisateurActiviteService.findActiviteById(anId);

        // then: the find method is invoke on the entity manager
        verify(entityManager).find(Activite.class, anId);
    }

    @Test
    public void testEntityManagerFindAnUtilisateurWhenUtilisateurIsSearchedById() {

        // when: trying to save the activite
        utilisateurActiviteService.findUtilisateurById(anId);

        // then: the find method is invoke on the entity manager
        verify(entityManager).find(Utilisateur.class, anId);
    }
}
