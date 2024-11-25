package bits.Service.impl;

import bits.Entity.Produit;
import bits.Repository.ProduitRepository;
import bits.Service.IProduitServices;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProduitServiceImp implements IProduitServices {

    @Autowired
    private ProduitRepository produitRepository;


    @Override
    public Produit insertProduit(Produit produit) {
        // Assurez-vous que le produit a toutes les informations nécessaires avant d'insérer
        if (produit.getTicket() == null) {
            // Assurez-vous que l'entité accepte un produit sans ticket
            produit.setTicket(null); // ou vous pouvez gérer ce cas selon votre logique
        }
        return produitRepository.save(produit);
    }

    @Override
    public Produit updateProduit(Long id, Produit produit) {
        Optional<Produit> existingProduit = produitRepository.findById(id);

        if (existingProduit.isPresent()) {
            Produit produitToUpdate = existingProduit.get();
            produitToUpdate.setNom(produit.getNom());
            produitToUpdate.setPrice(produit.getPrice());
            produitToUpdate.setTicket(produit.getTicket());  // Met à jour les détails du produit
            return produitRepository.save(produitToUpdate);  // Sauvegarde les modifications
        } else {
            throw new RuntimeException("Produit non trouvé avec l'id: " + id);  // Gestion simple des erreurs
        }
    }

    @Override
    public void deleteProduit(Long id) {
        // Vérifiez si le produit existe avant de tenter de le supprimer
        if (produitRepository.existsById(id)) {
            produitRepository.deleteById(id);  // Supprime le produit de la base de données
        } else {
            // Gérer le cas où le produit n'existe pas
            throw new RuntimeException("Produit non trouvé avec l'id: " + id);
        }
    }


    @Override
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    @Override
    public Optional<Produit> getProduitById(Long id) {
        return produitRepository.findById(id);
    }
}
