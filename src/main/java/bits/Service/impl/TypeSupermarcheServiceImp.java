package bits.Service.impl;

import bits.Entity.Produit;
import bits.Entity.Ticket;
import bits.Entity.TypeSupermarche;
import bits.Repository.TicketRepository;
import bits.Repository.TypeSupermarcheRepository;
import bits.Service.ITypeSupermarcheServices;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TypeSupermarcheServiceImp  implements ITypeSupermarcheServices {
    @Autowired
    private TypeSupermarcheRepository typeSupermarcheRepository;
    @Autowired
    private TicketRepository ticketRepository; // Assuming there is a repository for tickets

    @Override
    public TypeSupermarche insertSupermarche(TypeSupermarche typeSupermarche) {
        // Crée un nouvel objet TypeSupermarche avec seulement les champs nécessaires
        TypeSupermarche newSupermarche = new TypeSupermarche();
        newSupermarche.setNom(typeSupermarche.getNom());
        newSupermarche.setDescription(typeSupermarche.getDescription());

        // Sauvegarde le nouvel objet dans la base de données
        return typeSupermarcheRepository.save(newSupermarche);
    }


    @Override
    public TypeSupermarche updateSupermarche(Long id, TypeSupermarche typeSupermarche) {
        return null;
    }

    @Override
    public void deleteSupermarche(Long id) {
        typeSupermarcheRepository.deleteById(id);
    }

    @Override
    public List<TypeSupermarche> getAllSupermarches() {
        return typeSupermarcheRepository.findAll();
    }

    @Override
    public Optional<TypeSupermarche> getSupermarcheById(Long id) {
        return typeSupermarcheRepository.findById(id);
    }

    @Override
    public List<Ticket> getTicketsBySupermarcheId(Long id) {
        Optional<TypeSupermarche> supermarche = typeSupermarcheRepository.findById(id);
        if (supermarche.isPresent()) {
            return supermarche.get().getTickets();  // Retrieve the tickets associated with the supermarket
        }
        return null; // Handle case where no supermarket or tickets are found

    }

    @Override
    public Double getTotal(Long id) {
        Optional<TypeSupermarche> supermarche = typeSupermarcheRepository.findById(id);
        if (supermarche.isPresent()) {
            // Retrieve the list of tickets for the supermarket
            List<Ticket> tickets = supermarche.get().getTickets();

            // Initialize total sum
            double totalSum = 0.0;

            // Loop over each ticket
            for (Ticket ticket : tickets) {
                // Loop over each product in the ticket and sum the prices
                for (Produit produit : ticket.getProduits()) {
                    totalSum += produit.getPrice(); // Sum up the price of each product
                }
            }

            return totalSum; // Return the total sum
        } else {
            // Handle the case where the supermarket is not found
            throw new RuntimeException("Supermarché non trouvé avec l'ID: " + id);
        }
    }

    @Override
    public List<Produit> getProduitsBySupermarcheId(Long id) {
        // Use the existing service method to fetch tickets associated with the supermarket ID
        List<Ticket> tickets = getTicketsBySupermarcheId(id);

        if (tickets != null) {
            // Extract and return the list of products from the tickets
            return tickets.stream()
                    .flatMap(ticket -> ticket.getProduits().stream())
                    .distinct() // Ensure distinct products if needed
                    .collect(Collectors.toList());
        }
        return Collections.emptyList(); // Return an empty list if no tickets are found
    }

}
