package bits.Dto;

import bits.Entity.Ticket;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor

@SuperBuilder
public class ProduitDto {
    private Long id;
    private String nom;
    private double price;

    // Ticket peut être optionnel
    private Ticket ticket;
}
