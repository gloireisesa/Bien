package beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@RequestScoped
@Named
public class LieuBean {
    private String nom;
    private String description;
    private String latitude;
    private String longitude;

    // Getters et Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
   
    // Méthode pour ajouter un lieu
    public String ajouterLieu() {
        
        // Logique pour ajouter le lieu (remplacez cette partie par une logique de persistance si nécessaire)
        System.out.println("Lieu ajouté : " + nom + ", " + description + ", " + latitude + ", " + longitude);

        resetFields();
        
        
        return null; // 
    }

    // Méthode pour réinitialiser les champs (optionnelle, si vous souhaitez l'utiliser plus tard)
    private void resetFields() {
        //this.nom = null;
        //this.description = null;
        //this.latitude = null;
        //this.longitude = null;
    }
}