package friendsofmine.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Activite {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 1, max = 256)
    private String titre ;

    private String descriptif ;

    public Activite() { }

    public Activite(String titre, String descriptif) {
        this.titre = titre;
        this.descriptif = descriptif;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }

    public Long getId() {
        return id;
    }
}
