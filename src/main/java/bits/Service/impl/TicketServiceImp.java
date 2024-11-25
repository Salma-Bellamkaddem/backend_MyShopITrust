package bits.Service.impl;

import bits.Entity.Produit;
import bits.Entity.Ticket;
import bits.Repository.ProduitRepository;
import bits.Repository.TicketRepository;
import bits.Service.ITicketServices;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class TicketServiceImp  implements ITicketServices {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ProduitRepository produitRepository;
    @Override
    public Optional<Ticket> getTicketDetails(Long id) {
        // Fetch the ticket by its ID and return it along with its associated products
        return ticketRepository.findById(id);
    }

    @Override
    public Ticket addProductToTicket(Long ticketId, Produit produit) {
        // Fetch the ticket by its ID
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);

        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();

            // Add the new product to the ticket's product list
            produit.setTicket(ticket);  // Link the product to this ticket
            ticket.getProduits().add(produit);

            // Save the new product in the database
            produitRepository.save(produit);

            // Update and save the ticket
            return ticketRepository.save(ticket);
        } else {
            throw new RuntimeException("Ticket not found");
        }

    }
    @Override
    public List<Produit> getProductsByTicketId(Long ticketId) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
        if (ticketOptional.isPresent()) {
            Ticket ticket = ticketOptional.get();
            return ticket.getProduits();  // Assuming 'Ticket' has a list of products
        } else {
            throw new RuntimeException("Ticket not found");
        }
    }
    @Override
    public Ticket insertTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket updateTicket(Long id, Ticket ticket) {
        return null;
    }

    @Override
    public void deleteTicket(Long id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent()) {
            ticketRepository.delete(ticket.get());  // Supprime le ticket
        } else {
            throw new RuntimeException("Ticket not found with id: " + id);  // Gestion des erreurs
        }

    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }


    // Get ticket details along with associated products

}
