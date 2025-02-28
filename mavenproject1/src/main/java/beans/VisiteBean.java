package beans;

import business.SessionManager;
import business.VisiteEntrepriseBean;
import entities.Visite;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named("visiteBean")
@SessionScoped
public class VisiteBean implements Serializable {
    private Long idUtilisateur;
    private Long idLieu;
    private Date dateVisite;
    private int tempsPasse;
    private String observations;
    private double depenses;
    private boolean afficherFormulaireVisite;

    @Inject
    VisiteEntrepriseBean visiteEntrepriseBean;

    @Inject
    SessionManager sessionManager; // Pour récupérer l'ID de l'utilisateur connecté

    // Getters et Setters
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

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public boolean isAfficherFormulaireVisite() {
        return afficherFormulaireVisite;
    }

    public void setAfficherFormulaireVisite(boolean afficherFormulaireVisite) {
        this.afficherFormulaireVisite = afficherFormulaireVisite;
    }

    // Méthodes
    public void afficherFormulaireVisite() {
        afficherFormulaireVisite = true;
    }

    public void sauvegarderVisite() {
        try {
            // Récupérer l'ID de l'utilisateur connecté
            Long idUtilisateur = sessionManager.getIdUtilisateurConnecte();
            if (idUtilisateur == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Utilisateur non connecté", null));
                return;
            }

            // Validation des données
            if (idLieu == null || dateVisite == null || tempsPasse <= 0 || depenses < 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Veuillez remplir tous les champs obligatoires", null));
                return;
            }

            // Créer une nouvelle visite
            Visite visite = new Visite();
            visite.setIdUtilisateur(idUtilisateur);
            visite.setIdLieu(idLieu);
            visite.setDateVisite(dateVisite);
            visite.setTempsPasse(tempsPasse);
            visite.setObservations(observations);
            visite.setDepenses(depenses);

            // Sauvegarder la visite dans la base de données
            visiteEntrepriseBean.sauvegarderVisite(visite);

            // Afficher un message de succès
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Visite enregistrée avec succès", null));

            // Réinitialiser les champs du formulaire
            reinitialiserFormulaire();

            // Masquer le formulaire
            afficherFormulaireVisite = false;
        } catch (Exception e) {
            // Afficher un message d'erreur
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur lors de l'enregistrement de la visite", null));
            e.printStackTrace(); // Log de l'exception
        }
    }

    private void reinitialiserFormulaire() {
        idLieu = null;
        dateVisite = null;
        tempsPasse = 0;
        observations = null;
        depenses = 0.0;
    }
}