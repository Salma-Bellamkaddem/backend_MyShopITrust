package bits.Service;

import bits.Entity.Produit;

import java.util.List;
import java.util.Optional;

public interface IProduitServices {
    Produit insertProduit(Produit produit);
    Produit updateProduit(Long id, Produit produit);
    void deleteProduit(Long id);
    List<Produit> getAllProduits();
    Optional<Produit> getProduitById(Long id);

}
