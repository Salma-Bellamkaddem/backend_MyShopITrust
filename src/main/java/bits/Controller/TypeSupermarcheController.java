package bits.Controller;

import bits.Entity.Produit;
import bits.Entity.Ticket;
import bits.Entity.TypeSupermarche;
import bits.Service.ITypeSupermarcheServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/supermarches")
@CrossOrigin("*")
public class TypeSupermarcheController {

    @Autowired
    private ITypeSupermarcheServices typeSupermarcheServices;

    // Récupérer tous les supermarchés
    @GetMapping
    public ResponseEntity<List<TypeSupermarche>> getAllSupermarches() {
        List<TypeSupermarche> supermarches = typeSupermarcheServices.getAllSupermarches();
        return new ResponseEntity<>(supermarches, HttpStatus.OK);
    }

    // Récupérer un supermarché par ID
    @GetMapping("/{id}")
    public ResponseEntity<TypeSupermarche> getSupermarcheById(@PathVariable("id") Long id) {
        Optional<TypeSupermarche> supermarcheOptional = typeSupermarcheServices.getSupermarcheById(id);
        if (supermarcheOptional.isPresent()) {
            return new ResponseEntity<>(supermarcheOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Créer un nouveau supermarché
    @PostMapping
    public ResponseEntity<TypeSupermarche> createSupermarche(@RequestBody TypeSupermarche typeSupermarche) {
        TypeSupermarche createdSupermarche = typeSupermarcheServices.insertSupermarche(typeSupermarche);
        return new ResponseEntity<>(createdSupermarche, HttpStatus.CREATED);
    }

    // Mettre à jour un supermarché existant
    @PutMapping("/{id}")
    public ResponseEntity<TypeSupermarche> updateSupermarche(@PathVariable("id") Long id, @RequestBody TypeSupermarche typeSupermarche) {
        try {
            TypeSupermarche updatedSupermarche = typeSupermarcheServices.updateSupermarche(id, typeSupermarche);
            return new ResponseEntity<>(updatedSupermarche, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Supprimer un supermarché par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupermarche(@PathVariable("id") Long id) {
        try {
            typeSupermarcheServices.deleteSupermarche(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Récupérer les tickets associés à un supermarché
    @GetMapping("/{id}/tickets")
    public ResponseEntity<List<Ticket>> getTicketsBySupermarcheId(@PathVariable("id") Long id) {
        List<Ticket> tickets = typeSupermarcheServices.getTicketsBySupermarcheId(id);
        if (tickets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        }
    }
    @GetMapping("/{id}/total")
    public Double getTotalBySupermarcheId(@PathVariable("id") Long id) {

        return typeSupermarcheServices.getTotal(id);


    }
    @GetMapping("/{id}/produits")
    public ResponseEntity<List<Produit>> getProduitsBySupermarcheId(@PathVariable("id") Long id) {
        List<Produit> produits = typeSupermarcheServices.getProduitsBySupermarcheId(id);
        return ResponseEntity.ok(produits);
    }

}