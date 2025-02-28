package entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "visite")
public class Visite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idutilisateur", nullable = false)
    private Long idUtilisateur;

    @Column(name = "idlieu", nullable = false)
    private Long idLieu;

    @Column(name = "datevisite", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateVisite;

    @Column(name = "tempspasse", nullable = false)
    private int tempsPasse;

    @Column(name = "observations", length = 500)
    private String observations;

    @Column(name = "depenses", nullable = false)
    private double depenses;

    // Constructeur par défaut (requis par JPA)
    public Visite() {
    }

    // Constructeur avec arguments (facultatif mais utile)
    public Visite(Long idUtilisateur, Long idLieu, Date dateVisite, int tempsPasse, String observations, double depenses) {
        this.idUtilisateur = idUtilisateur;
        this.idLieu = idLieu;
        this.dateVisite = dateVisite;
        this.tempsPasse = tempsPasse;
        this.observations = observations;
        this.depenses = depenses;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Long getIdLieu() {
        return idLieu;
    }

    public void setIdLieu(Long idLieu) {
        this.idLieu = idLieu;
    }

    public Date getDateVisite() {
        return dateVisite;
    }

    public void setDateVisite(Date dateVisite) {
        this.dateVisite = dateVisite;
    }

    public int getTempsPasse() {
        return tempsPasse;
    }

    public void setTempsPasse(int tempsPasse) {
        this.tempsPasse = tempsPasse;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public double getDepenses() {
        return depenses;
    }

    public void setDepenses(double depenses) {
        this.depenses = depenses;
    }
}