package friendsofmine;

import friendsofmine.domain.Activite;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ActiviteTest {

    private static Validator validator;

    @BeforeAll
    public static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testTitreNonVideEtDescrptif() {
        // given: une Activite avec un titre et un descriptif valides
        Activite act = new Activite("unTitre", "unDescriptif");
        // when: on vérifie les contraintes de validation de l'Activite
        // then: il n'y a pas d'erreur de validation
        Assertions.assertTrue(validator.validate(act).isEmpty());
    }

    @Test
    public void testTitreNonVideEtDescriptifVide() {
        // given: une Activite avec un titre et un descriptif vide
        Activite act = new Activite("unTitre", "");
        // when: on vérifie les contraintes de validation de l'Activite
        // then: il n'y a pas d'erreur de validation
        Assertions.assertTrue(validator.validate(act).isEmpty());
    }

    @Test
    public void testTitreNonVideEtDescriptifNull() {
        // given: une Activite avec un titre et un descriptif null
        Activite act = new Activite("unTitre", null);
        // when: on vérifie les contraintes de validation de l'Activite
        // then: il n'y a pas d'erreur de validation
        Assertions.assertTrue(validator.validate(act).isEmpty());
    }

    @Test
    public void testTitreVide() {
        // given: une Activite avec un titre vide et un descriptif
        Activite act = new Activite("", "unDescriptif");
        // when: on vérifie les contraintes de validation de l'Activite
        // then: il y a une erreur de validation
        Assertions.assertFalse(validator.validate(act).isEmpty());
    }

    @Test
    public void testTitreNull() {
        // given: une Activite avec un titre null et un descriptif valides
        Activite act = new Activite(null, "unDescriptif");
        // when: on vérifie les contraintes de validation de l'Activite
        // then: il y a une erreur de validation
        Assertions.assertFalse(validator.validate(act).isEmpty());
    }
}
