package bits.Controller;

import bits.Entity.Produit;
import bits.Entity.Ticket;
import bits.Service.ITicketServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tickets")
@CrossOrigin("*")
public class TicketController {

    @Autowired
    private ITicketServices ticketServices;

    // Récupérer tous les tickets
    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = ticketServices.getAllTickets();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    // Récupérer les détails d'un ticket par ID
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketDetails(@PathVariable("id") Long id) {
        Optional<Ticket> ticketOptional = ticketServices.getTicketDetails(id);
        if (ticketOptional.isPresent()) {
            return new ResponseEntity<>(ticketOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Ajouter un nouveau ticket
    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        Ticket createdTicket = ticketServices.insertTicket(ticket);
        return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
    }
    // Récupérer tous les produits associés à un ticket par ID
    @GetMapping("/{ticketId}/produits")
    public ResponseEntity<List<Produit>> getProductsByTicketId(@PathVariable("ticketId") Long ticketId) {
        try {
            List<Produit> produits = ticketServices.getProductsByTicketId(ticketId);
            return new ResponseEntity<>(produits, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Mettre à jour un ticket existant
    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable("id") Long id, @RequestBody Ticket ticket) {
        Ticket updatedTicket = ticketServices.updateTicket(id, ticket);
        if (updatedTicket != null) {
            return new ResponseEntity<>(updatedTicket, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Supprimer un ticket par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable("id") Long id) {
        try {
            ticketServices.deleteTicket(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Ajouter un produit à un ticket
    @PostMapping("/{ticketId}/produits")
    public ResponseEntity<Ticket> addProductToTicket(@PathVariable("ticketId") Long ticketId, @RequestBody Produit produit) {
        try {
            Ticket updatedTicket = ticketServices.addProductToTicket(ticketId, produit);
            return new ResponseEntity<>(updatedTicket, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
