package bits.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime startDate;
private String nomTicket;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(nullable = false)
    private TypeSupermarche typeSupermarche;


    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<Produit> produits;
}
