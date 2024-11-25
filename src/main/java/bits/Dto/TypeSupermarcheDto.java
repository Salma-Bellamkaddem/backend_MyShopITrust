package bits.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeSupermarcheDto {
    private String nom;
    private String description;
    private List<TicketDto> tickets;
}
