package friendsofmine;

import friendsofmine.services.UtilisateurActiviteService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class DataLoaderIntegrationTest {

    @Autowired
    private UtilisateurActiviteService utilisateurActiviteService;

    @Autowired
    private DataLoader dataLoader;

    @Test
    public void testCheckLeon() {
        // l'utilisateur Leon est en base
        Assertions.assertEquals("gb@gmail.com", dataLoader.getLeon().getEmail());
        Assertions.assertEquals("Ferland", dataLoader.getLeon().getNom());
        Assertions.assertEquals("Léon", dataLoader.getLeon().getPrenom());
        Assertions.assertEquals("M", dataLoader.getLeon().getSexe());
    }

    @Test
    public void testCheckJulien() {
        // l'utilisateur Julien est en base
        Assertions.assertEquals("jb@hotmail.fr", dataLoader.getJulien().getEmail());
        Assertions.assertEquals("Breton", dataLoader.getJulien().getNom());
        Assertions.assertEquals("Julien", dataLoader.getJulien().getPrenom());
        Assertions.assertEquals("M", dataLoader.getJulien().getSexe());
    }

    @Test
    public void testCheckYseult() {
        // l'utilisattrice Yseult est en base
        Assertions.assertEquals("yseult@gmail.com", dataLoader.getYseult().getEmail());
        Assertions.assertEquals("Baril", dataLoader.getYseult().getNom());
        Assertions.assertEquals("Yseult", dataLoader.getYseult().getPrenom());
        Assertions.assertEquals("F", dataLoader.getYseult().getSexe());
    }

    @Test
    public void testCheckArnaude() {
        // l'utilisateur Arnaude est en base
        Assertions.assertEquals("arnaude@yahoo.com", dataLoader.getArnaude().getEmail());
        Assertions.assertEquals("Bilodeau", dataLoader.getArnaude().getNom());
        Assertions.assertEquals("Arnaude", dataLoader.getArnaude().getPrenom());
        Assertions.assertEquals("F", dataLoader.getArnaude().getSexe());
    }

    @Test
    public void testGuitare() {
        // l'activité Guitare est en base
        Assertions.assertEquals("Guitare", dataLoader.getGuitare().getTitre());
        Assertions.assertEquals("Matériel non fourni", dataLoader.getGuitare().getDescriptif());
        Assertions.assertEquals(dataLoader.getGuitare().getResponsable().getId(),
                dataLoader.getLeon().getId(), "Léon est le responsable");
    }

    @Test
    public void testPetanque() {
        // l'activité Petanque est en base
        Assertions.assertEquals("Pétanque", dataLoader.getPetanque().getTitre());
        Assertions.assertEquals("Seulement à partir de mai", dataLoader.getPetanque().getDescriptif());
        Assertions.assertEquals(dataLoader.getPetanque().getResponsable().getId(),
                dataLoader.getYseult().getId(), "Yseult est la responsable");
    }

    @Test
    public void testSieste() {
        // l'activité Sieste est en base
        Assertions.assertEquals("Sieste", dataLoader.getSieste().getTitre());
        Assertions.assertEquals("Tous les jours, dans la salle de repos", dataLoader.getSieste().getDescriptif());
        Assertions.assertEquals(dataLoader.getSieste().getResponsable().getId(),
                dataLoader.getJulien().getId(), "Julien est le responsable");
    }

    @Test
    public void testFooting() {
        // l'activité Footing est en base
        Assertions.assertEquals("Footing", dataLoader.getFooting().getTitre());
        Assertions.assertEquals("Tous les midis", dataLoader.getFooting().getDescriptif());
        Assertions.assertEquals(dataLoader.getFooting().getResponsable().getId(),
                dataLoader.getArnaude().getId(), "Arnaude est la responsable");
    }

    @Test
    public void testCardio() {
        // l'activité Cardio est en base
        Assertions.assertEquals("Cardio", dataLoader.getCardio().getTitre());
        Assertions.assertEquals("Tous les samedis", dataLoader.getCardio().getDescriptif());
        Assertions.assertEquals(dataLoader.getCardio().getResponsable().getId(),
                dataLoader.getLeon().getId(), "Léon est le responsable");
    }

    @Test
    public void testSophorlogie() {
        // l'activité Sophrologie est en base
        Assertions.assertEquals("Sophrologie", dataLoader.getSophrologie().getTitre());
        Assertions.assertEquals("En salle B-123", dataLoader.getSophrologie().getDescriptif());
        Assertions.assertEquals(dataLoader.getSophrologie().getResponsable().getId(),
                dataLoader.getLeon().getId(), "Léon est le responsable");
    }


}
