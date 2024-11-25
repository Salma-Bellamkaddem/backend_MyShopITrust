package bits.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {
    private LocalDateTime startDate;
    private double totalPrice;
    private List<ProduitDto> produits;
}
