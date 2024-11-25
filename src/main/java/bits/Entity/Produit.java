package bits.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   private String nom ;
   private double price ;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = true) // Permet que ticket soit null
    private Ticket ticket;

}
