package business;

import entities.Visite;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@Stateless
@LocalBean
public class VisiteEntrepriseBean {

    @PersistenceContext
    private EntityManager entityManager;

    // Méthode pour ajouter une nouvelle visite
    public void ajouterVisite(Visite visite) {
        entityManager.persist(visite);
    }

    // Méthode pour sauvegarder une visite (équivalent à ajouterVisite)
    @Transactional
    public void sauvegarderVisite(Visite visite) {
        entityManager.persist(visite);
    }

    // Méthode pour obtenir toutes les visites
    public List<Visite> obtenirVisites() {
        return entityManager.createQuery("SELECT v FROM Visite v", Visite.class).getResultList();
    }

    // Méthode pour obtenir une visite par ID
    public Visite obtenirVisiteParId(Long id) {
        return entityManager.find(Visite.class, id);
    }

    // Méthode pour mettre à jour une visite
    @Transactional
    public void mettreAJourVisite(Visite visite) {
        entityManager.merge(visite);
    }

    // Méthode pour supprimer une visite
    @Transactional
    public void supprimerVisite(Long id) {
        Visite visite = obtenirVisiteParId(id);
        if (visite != null) {
            entityManager.remove(visite);
        }
    }
}