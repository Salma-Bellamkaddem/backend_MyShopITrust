package bits.Repository;

import bits.Entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProduitRepository  extends JpaRepository<Produit,Long> {

    void deleteById(Long id );

}
