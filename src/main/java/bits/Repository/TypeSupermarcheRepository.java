package bits.Repository;

import bits.Entity.TypeSupermarche;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeSupermarcheRepository extends JpaRepository<TypeSupermarche ,Long>{
    List<TypeSupermarche> findByNom(String nom);
void deleteByNom( String nom);
}

