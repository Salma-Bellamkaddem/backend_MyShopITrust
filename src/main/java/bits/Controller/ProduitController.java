package bits.Controller;

import bits.Entity.Produit;
import bits.Service.IProduitServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produits")
public class ProduitController {

    @Autowired
    private IProduitServices produitServices;

    @PostMapping
    public ResponseEntity<Produit> createProduit(@RequestBody Produit produit) {
        Produit savedProduit = produitServices.insertProduit(produit);
        return new ResponseEntity<>(savedProduit, HttpStatus.CREATED);  // Retourne le produit créé avec le code de statut HTTP 201
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable("id") Long id, @RequestBody Produit produit) {
        try {
            Produit updatedProduit = produitServices.updateProduit(id, produit);
            return new ResponseEntity<>(updatedProduit, HttpStatus.OK);  // Retourne le produit mis à jour avec le code de statut HTTP 200
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);  // Retourne une erreur 404 si le produit n'est pas trouvé
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable("id") Long id) {
        try {
            produitServices.deleteProduit(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Retourne un code de statut HTTP 204 pour indiquer que la suppression a réussi
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Retourne une erreur 404 si le produit n'est pas trouvé
        }
    }

    @GetMapping
    public ResponseEntity<List<Produit>> getAllProduits() {
        List<Produit> produits = produitServices.getAllProduits();
        return new ResponseEntity<>(produits, HttpStatus.OK);  // Retourne la liste des produits avec le code de statut HTTP 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable("id") Long id) {
        Optional<Produit> produit = produitServices.getProduitById(id);
        if (produit.isPresent()) {
            return new ResponseEntity<>(produit.get(), HttpStatus.OK);  // Retourne le produit trouvé avec le code de statut HTTP 200
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Retourne une erreur 404 si le produit n'est pas trouvé
        }
    }
}
