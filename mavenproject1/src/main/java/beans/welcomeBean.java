/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named("welcomeBean")
@RequestScoped
public class welcomeBean {
    private String nom;
    private String message;

    // Getter pour 'nom'
    public String getNom() {
        return nom;
    }

    // Setter pour 'nom' (obligatoire pour que JSF puisse modifier la valeur)
    public void setNom(String nom) {
        this.nom = nom;
    }

    // Getter pour 'message'
    public String getMessage() {
        return message;
    }

    // Méthode pour afficher le message
    public void afficherMessage() {
        if (nom != null && !nom.trim().isEmpty()) {
            message = "Selamat datang, " + nom + "!";
        } else {
            message = "Veuillez entrer votre nom.";
        }
    }
}