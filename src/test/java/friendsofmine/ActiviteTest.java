package friendsofmine;

import friendsofmine.domain.Activite;
import friendsofmine.domain.Utilisateur;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ActiviteTest {

    private static Validator validator;
    private Utilisateur utilisateur = new Utilisateur("nom", "prenom", "toto@toto.fr", "M");;

    @BeforeAll
    public static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testTitreNonVideEtDescrptif() {
        // given: une Activite avec un titre, un descriptif et un utilisateur comme responsable
        Activite act = new Activite("unTitre", "unDescriptif", utilisateur);
        // when: on vérifie les contraintes de validation de l'Activite
        // then: il n'y a pas d'erreur de validation
        Assertions.assertTrue(validator.validate(act).isEmpty());
    }

    @Test
    public void testTitreNonVideEtDescriptifVide() {
        // given: une Activite avec un titre, un descriptif vide et un utilisateur comme responsable
        Activite act = new Activite("unTitre", "", utilisateur);
        // when: on vérifie les contraintes de validation de l'Activite
        // then: il n'y a pas d'erreur de validation
        Assertions.assertTrue(validator.validate(act).isEmpty());
    }

    @Test
    public void testTitreNonVideEtDescriptifNull() {
        // given: une Activite avec un titre, un descriptif null et un utilisateur comme responsable
        Activite act = new Activite("unTitre", null, utilisateur);
        // when: on vérifie les contraintes de validation de l'Activite
        // then: il n'y a pas d'erreur de validation
        Assertions.assertTrue(validator.validate(act).isEmpty());
    }

    @Test
    public void testTitreVide() {
        // given: une Activite avec un titre vide, un descriptif et un utilisateur comme responsable
        Activite act = new Activite("", "unDescriptif", utilisateur);
        // when: on vérifie les contraintes de validation de l'Activite
        // then: il y a une erreur de validation
        Assertions.assertFalse(validator.validate(act).isEmpty());
    }

    @Test
    public void testTitreNull() {
        // given: une Activite avec un titre null, un descriptif valides et un utilisateur comme responsable
        Activite act = new Activite(null, "unDescriptif", utilisateur);
        // when: on vérifie les contraintes de validation de l'Activite
        // then: il y a une erreur de validation
        Assertions.assertFalse(validator.validate(act).isEmpty());
    }

    @Test
    public void testResponsableNull() {
        // given: une Activite avec un titre, un descriptif et un utilisateur null comme responsable
        Activite act = new Activite("unTitre", "unDescriptif", null);
        // when: on vérifie les contraintes de validation de l'Activite
        // then: il y a une erreur de validation
        Assertions.assertFalse(validator.validate(act).isEmpty());
    }

    @Test
    public void testAValidActiviteHasAResponsable() {
        // given: une Activite avec un titre, un descriptif et un utilisateur comme responsable
        Activite act = new Activite("unTitre", "unDescriptif", utilisateur);
        // when: on vérifie la présence d'un utilisateur nommé responsable
        // then: l'utilisateur est le responsable
        Assertions.assertEquals(utilisateur, act.getResponsable());
    }
}
