package bits.Service;

import bits.Entity.Produit;
import bits.Entity.Ticket;

import java.util.List;
import java.util.Optional;

public interface ITicketServices {
    Optional<Ticket> getTicketDetails(Long id);

    // Add a product to an existing ticket
    Ticket addProductToTicket(Long ticketId, Produit produit);
    // Insert a new ticket
    Ticket insertTicket(Ticket ticket);

    // Update an existing ticket
    Ticket updateTicket(Long id, Ticket ticket);

    // Delete a ticket by ID
    void deleteTicket(Long id);

    // Get all tickets
    List<Ticket> getAllTickets();

    // Get a ticket by ID
     List<Produit> getProductsByTicketId(Long ticketId);

}
