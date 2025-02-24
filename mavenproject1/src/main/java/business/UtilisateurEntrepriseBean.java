/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package business;

import entities.Utilisateur;
import jakarta.ejb.Stateless;
import jakarta.ejb.LocalBean;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author dell
 */
@Stateless
@LocalBean
public class UtilisateurEntrepriseBean {
    
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void ajouterUtilisateurEntreprise(String username, String email, String password, String description) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        Utilisateur utilisateur = new Utilisateur(username, email, hashedPassword, description);
        em.persist(utilisateur);
    }
    public boolean verifierMotDePasse(String password, String hashedPassword) { return BCrypt.checkpw(password, hashedPassword); }  

    public List<Utilisateur> listerTousLesUtilisateurs() {
        return em.createQuery("SELECT u FROM Utilisateur u", Utilisateur.class).getResultList();
    }
    
    public boolean utilisateurExisteDeja(String username, String email) {
    // Vérifier si un utilisateur avec le même nom d'utilisateur existe déjà
    Long countByUsername = em.createQuery("SELECT COUNT(u) FROM Utilisateur u WHERE u.username = :username", Long.class)
                             .setParameter("username", username)
                             .getSingleResult();

    // Vérifier si un utilisateur avec la même adresse e-mail existe déjà
    Long countByEmail = em.createQuery("SELECT COUNT(u) FROM Utilisateur u WHERE u.email = :email", Long.class)
                          .setParameter("email", email)
                          .getSingleResult();

    // Retourner true si un utilisateur existe déjà avec le même nom d'utilisateur ou la même adresse e-mail
    return countByUsername > 0 || countByEmail > 0;
}

    @Transactional
    public void supprimerUtilisateur(Long id) {
        Utilisateur utilisateur = em.find(Utilisateur.class, id);
        if (utilisateur != null) {
            em.remove(utilisateur);
        }
    }

    public Utilisateur trouverUtilisateurParId(Long id) {
        return em.find(Utilisateur.class, id);
    }

    public Utilisateur trouverUtilisateurParEmail(String email) {
        try {
            return em.createQuery("SELECT u FROM Utilisateur u WHERE u.email = :email", Utilisateur.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
