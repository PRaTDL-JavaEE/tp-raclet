package friendsofmine;

import friendsofmine.domain.Utilisateur;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class UtilisateurTest {

    private static Validator validator;

    @BeforeAll
    public static void setupContext() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testUtilisateurFemme() {
        // given: un Utilisateur avec un nom, un prénom, un email et un sexe valides
        Utilisateur util = new Utilisateur("Dupont", "Jeanne", "jd@jd.com", "F");
        // when: on vérifie les contraintes de validation de l'Utilisateur
        // then: il n'y a pas d'erreur de validation
        Assertions.assertTrue(validator.validate(util).isEmpty());
    }

    @Test
    public void testUtilisateurHomme() {
        // given: un Utilisateur avec un nom, un prénom, un email et un sexe valides
        Utilisateur util = new Utilisateur("Durand", "Jacques", "jd@jd.com", "M");
        // when: on vérifie les contraintes de validation de l'Utilisateur
        // then: il n'y a pas d'erreur de validation
        Assertions.assertTrue(validator.validate(util).isEmpty());
    }

    @Test
    public void testUtilisateurNomNull() {
        // given: un Utilisateur avec un nom null
        Utilisateur util = new Utilisateur(null, "Jacques", "jd@jd.com", "M");
        // when: on vérifie les contraintes de validation de l'Utilisateur
        // then: il y a une erreur de validation
        Assertions.assertFalse(validator.validate(util).isEmpty());
    }

    @Test
    public void testUtilisateurNomVide() {
        // given: un Utilisateur avec un nom vide
        Utilisateur util = new Utilisateur("", "Jacques", "jd@jd.com", "M");
        // when: on vérifie les contraintes de validation de l'Utilisateur
        // then: il y a une erreur de validation
        Assertions.assertFalse(validator.validate(util).isEmpty());
    }

    @Test
    public void testUtilisateurPrenomNull() {
        // given: un Utilisateur avec un prénom null
        Utilisateur util = new Utilisateur("Durand", null, "jd@jd.com", "M");
        // when: on vérifie les contraintes de validation de l'Utilisateur
        // then: il y a une erreur de validation
        Assertions.assertFalse(validator.validate(util).isEmpty());
    }

    @Test
    public void testUtilisateurPrenomVide() {
        // given: un Utilisateur avec un prénom vide
        Utilisateur util = new Utilisateur("Durand", "", "jd@jd.com", "M");
        // when: on vérifie les contraintes de validation de l'Utilisateur
        // then: il y a une erreur de validation
        Assertions.assertFalse(validator.validate(util).isEmpty());
    }

    @Test
    public void testUtilisateurEmailSansArobase() {
        // given: un Utilisateur avec un email non-valide
        Utilisateur util = new Utilisateur("Durand", "Eric", "jd.jd.com", "M");
        // when: on vérifie les contraintes de validation de l'Utilisateur
        // then: il y a une erreur de validation
        Assertions.assertFalse(validator.validate(util).isEmpty());
    }

    @Test
    public void testUtilisateurEmailNull() {
        // given: un Utilisateur avec un email null
        Utilisateur util = new Utilisateur("Durand", "Eric", null, "M");
        // when: on vérifie les contraintes de validation de l'Utilisateur
        // then: il y a une erreur de validation
        Assertions.assertFalse(validator.validate(util).isEmpty());
    }

    @Test
    public void testUtilisateurSexeInvalide() {
        // given: un Utilisateur avec un sexe qui n'est pas M ou F
        Utilisateur util = new Utilisateur("Durand", "Eric", "jd@jd.com", "V");
        // when: on vérifie les contraintes de validation de l'Utilisateur
        // then: il y a une erreur de validation
        Assertions.assertFalse(validator.validate(util).isEmpty());
    }

    @Test
    public void testUtilisateurSexeNull() {
        // given: un Utilisateur avec un sexe  null
        Utilisateur util = new Utilisateur("Durand", "Eric", "jd@jd.com", null);
        // when: on vérifie les contraintes de validation de l'Utilisateur
        // then: il y a une erreur de validation
        Assertions.assertFalse(validator.validate(util).isEmpty());
    }

}
