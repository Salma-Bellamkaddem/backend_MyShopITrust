package bits.Service;

import bits.Entity.Produit;
import bits.Entity.Ticket;
import bits.Entity.TypeSupermarche;

import java.util.List;
import java.util.Optional;

public interface ITypeSupermarcheServices {
    // Method to insert a new supermarket
    TypeSupermarche insertSupermarche(TypeSupermarche typeSupermarche);

    // Method to update an existing supermarket
    TypeSupermarche updateSupermarche(Long id, TypeSupermarche typeSupermarche);

    // Method to delete a supermarket by its ID
    void deleteSupermarche(Long id);

    // Method to get all supermarkets
    List<TypeSupermarche> getAllSupermarches();

    // Method to get a supermarket by its ID
    Optional<TypeSupermarche> getSupermarcheById(Long id);

    // Method to get a list of tickets associated with a specific supermarket ID
    List<Ticket> getTicketsBySupermarcheId(Long id);
    Double getTotal(Long id);
    List<Produit> getProduitsBySupermarcheId(Long id);
}
