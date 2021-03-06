package friendsofmine.services;

import friendsofmine.domain.Activite;
import friendsofmine.domain.Utilisateur;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UtilisateurActiviteService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Activite save(Activite activite) {
        activite.setResponsable(save(activite.getResponsable()));
        Activite a = entityManager.merge(activite);
        Utilisateur resp = a.getResponsable();
        resp.add(a);
        return a;
    }

    @Transactional
    public Utilisateur save(Utilisateur utilisateur) {
        return entityManager.merge(utilisateur);
    }


    public Activite findActiviteById(Long id) {
        return entityManager.find(Activite.class, id);
    }

    public Utilisateur findUtilisateurById(Long id) {
        return entityManager.find(Utilisateur.class, id);
    }

    public List<Utilisateur> findAllUtilisateur() {
        TypedQuery<Utilisateur> query = entityManager.createQuery("select a from Utilisateur a order by a.nom", Utilisateur.class);
        return query.getResultList();
    }

    @Transactional
    public void deleteUtilisateurById(Long id) {
        entityManager.remove(findUtilisateurById(id));
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Utilisateur> findAllUtilisateurBySexe(String sexe) {
        if (sexe.equals("M") || sexe.equals("F")) {
            TypedQuery<Utilisateur> query = entityManager.createQuery("from Utilisateur a where a.sexe=:sex order by a.nom", Utilisateur.class);
            query.setParameter("sex", sexe);
            return query.getResultList();
        }
        return findAllUtilisateur();
    }

}
