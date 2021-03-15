package friendsofmine.services;

import friendsofmine.domain.Activite;
import friendsofmine.domain.Utilisateur;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class UtilisateurActiviteService {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Activite activite) {
        entityManager.persist(activite);
    }

    public void save(Utilisateur utilisateur) {
        entityManager.persist(utilisateur);
    }

    public Activite findActiviteById(Long id) {
        return entityManager.find(Activite.class, id);
    }

    public Utilisateur findUtilisateurById(Long id) {
        return entityManager.find(Utilisateur.class, id);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
