package com.example.bibliotheque.controller; 
 
import com.example.bibliotheque.model.Livre; 
import com.example.bibliotheque.service.LivreService;
import com.example.bibliotheque.dto.LivreDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; 
 
import java.util.List; 
 
@Tag(name = "Gestion des Livres", description = "Endpoints pour la gestion des livres")
@RestController
@RequestMapping("/api/livres") 
public class LivreController { 
    @Autowired 
    private LivreService livreService; 

    @Operation(
        summary = "Récupère tous les livres",
        description = "Retourne la liste complète de tous les livres de la bibliothèque",
        responses = {
            @ApiResponse(responseCode = "200", description = "Liste des livres récupérée avec succès")
        }
    )
    @GetMapping 
    public List<Livre> getAllLivres() { 
        return livreService.getAllLivres(); 
    } 
 
    @Operation(
        summary = "Récupère un livre par son ID",
        description = "Retourne un livre complet avec tous ses détails",
        responses = {
            @ApiResponse(responseCode = "200", description = "Livre trouvé"),
            @ApiResponse(responseCode = "404", description = "Livre non trouvé")
        }
    )
    @GetMapping("/{id}") 
    public Livre getLivreById(@PathVariable Long id) { 
        return livreService.getLivreById(id); 
    } 

    @Operation(
        summary = "Recherche des livres",
        description = "Recherche des livres par mot-clé avec pagination",
        responses = {
            @ApiResponse(responseCode = "200", description = "Résultats de recherche trouvés"),
            @ApiResponse(responseCode = "400", description = "Paramètres de recherche invalides")
        }
    )
    @GetMapping("/search")
    public List<Livre> search(
        @RequestParam @NotBlank String keyword,
        @RequestParam @Min(1) int page) {
        return livreService.searchLivres(keyword, page);
    }

    @Operation(
        summary = "Crée un nouveau livre",
        description = "Ajoute un nouveau livre à la bibliothèque",
        requestBody = @RequestBody(
            content = @Content(
                examples = @ExampleObject(
                    value = "{\"titre\":\"Nouveau Livre\",\"auteur\":\"Auteur\",\"isbn\":\"978-1234567890\",\"anneePublication\":2023}"
                )
            )
        ),
        responses = {
            @ApiResponse(responseCode = "201", description = "Livre créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données de livre invalides")
        }
    )
    @PostMapping
    public ResponseEntity<Livre> createLivre(@Valid @org.springframework.web.bind.annotation.RequestBody Livre livre) {
        Livre savedLivre = livreService.saveLivre(livre);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLivre);
    }

    @Operation(
        summary = "Crée un nouveau livre (v2)",
        description = "Ajoute un nouveau livre à la bibliothèque en utilisant un DTO",
        responses = {
            @ApiResponse(responseCode = "201", description = "Livre créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données de livre invalides")
        }
    )
    @PostMapping("/v2")
    public ResponseEntity<Livre> createLivreV2(@Valid @RequestBody LivreDTO dto) {
        // Conversion DTO -> Entity
        Livre livre = new Livre();
        livre.setTitre(dto.getTitre());
        livre.setIsbn(dto.getIsbn());
        
        Livre savedLivre = livreService.saveLivre(livre);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLivre);
    }

    @Operation(
        summary = "Supprime un livre",
        description = "Supprime un livre de la bibliothèque",
        responses = {
            @ApiResponse(responseCode = "204", description = "Livre supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Livre non trouvé")
        }
    )
    @DeleteMapping("/{id}") 
    public void deleteLivre(@PathVariable Long id) { 
        livreService.deleteLivre(id); 
    }

    @Operation(
        summary = "Met à jour un livre",
        description = "Modifie les informations d'un livre existant",
        responses = {
            @ApiResponse(responseCode = "200", description = "Livre mis à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Livre non trouvé"),
            @ApiResponse(responseCode = "400", description = "Données de livre invalides")
        }
    )
    @PutMapping("/{id}") 
    public Livre updateLivre(@PathVariable Long id, @RequestBody Livre livreDetails) { 
        return livreService.updateLivre(id, livreDetails); 
    } 

}