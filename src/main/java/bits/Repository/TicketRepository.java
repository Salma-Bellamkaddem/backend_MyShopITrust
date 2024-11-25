package bits.Repository;

import bits.Entity.Produit;
import bits.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
   // List<Ticket> findByProduits(Produit produit);
    void deleteById(Long id );
    //Optional<Ticket> findById(String  nom);
    //void deleteByNom(String mon );
}
