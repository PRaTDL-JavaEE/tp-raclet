package friendsofmine.domain;

import jdk.jshell.execution.Util;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Utilisateur {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull @Size(min = 1)
    private String nom ;

    @NotNull @Size(min = 1)
    private String prenom ;

    @NotNull @Email
    private String email ;

    @NotNull @Pattern(regexp = "^[MF]{1}$")
    private String sexe;

    @OneToMany(mappedBy = "responsable")
    private Collection<Activite> activites;

    public Utilisateur() { }

    public Utilisateur(String unNom, String unPrenom, String unEmail,
                       String unSexe) {
        nom = unNom;
        prenom = unPrenom;
        email = unEmail;
        sexe = unSexe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Long getId() {
        return id;
    }

    public Collection<Activite> getActivites() {
        return activites;
    }

    public void setActivites(Collection<Activite> activites) {
        this.activites = activites;
    }

    public void add(Activite activite) {
        if (getActivites() == null)
            setActivites(new ArrayList<>());
        getActivites().add(activite);
    }
}
