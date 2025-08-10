package com.example.bibliotheque.controller;

import com.example.bibliotheque.model.Emprunt;
import com.example.bibliotheque.service.EmpruntService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Gestion des Emprunts", description = "Endpoints pour la gestion des emprunts de livres")
@RestController
@RequestMapping("/api/emprunts")
public class EmpruntController {

    @Autowired
    private EmpruntService empruntService;

    @Operation(
        summary = "Récupère tous les emprunts",
        description = "Retourne la liste complète de tous les emprunts",
        responses = {
            @ApiResponse(responseCode = "200", description = "Liste des emprunts récupérée avec succès")
        }
    )
    @GetMapping
    public List<Emprunt> getAllEmprunts() {
        return empruntService.getAllEmprunts();
    }

    @Operation(
        summary = "Récupère un emprunt par son ID",
        description = "Retourne un emprunt spécifique basé sur son identifiant",
        responses = {
            @ApiResponse(responseCode = "200", description = "Emprunt trouvé"),
            @ApiResponse(responseCode = "404", description = "Emprunt non trouvé")
        }
    )
    @GetMapping("/{id}")
    public Emprunt getEmpruntById(@PathVariable Long id) {
        return empruntService.getEmpruntById(id);
    }

    @Operation(
        summary = "Récupère les emprunts en retard",
        description = "Retourne la liste des emprunts dont la date de retour est dépassée",
        responses = {
            @ApiResponse(responseCode = "200", description = "Liste des emprunts en retard")
        }
    )
    @GetMapping("/retard")
    public List<Emprunt> getEmpruntsEnRetard() {
        return empruntService.getEmpruntsEnRetard();
    }

    @Operation(
        summary = "Crée un nouvel emprunt",
        description = "Enregistre un nouvel emprunt de livre",
        requestBody = @RequestBody(
            content = @Content(
                examples = @ExampleObject(
                    value = "{\"dateEmprunt\":\"2025-08-10\",\"dateRetour\":\"2025-08-24\",\"statut\":\"EN_COURS\",\"emprunteur\":\"Jean Dupont\",\"livre\":{\"id\":1}}"
                )
            )
        ),
        responses = {
            @ApiResponse(responseCode = "201", description = "Emprunt créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données d'emprunt invalides")
        }
    )
    @PostMapping
    public ResponseEntity<Emprunt> createEmprunt(@Valid @org.springframework.web.bind.annotation.RequestBody Emprunt emprunt) {
        Emprunt savedEmprunt = empruntService.saveEmprunt(emprunt);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmprunt);
    }

    @Operation(
        summary = "Met à jour un emprunt",
        description = "Modifie les informations d'un emprunt existant",
        requestBody = @RequestBody(
            content = @Content(
                examples = @ExampleObject(
                    value = "{\"dateEmprunt\":\"2025-08-10\",\"dateRetour\":\"2025-08-24\",\"statut\":\"TERMINE\",\"emprunteur\":\"Jean Dupont\",\"livre\":{\"id\":1}}"
                )
            )
        ),
        responses = {
            @ApiResponse(responseCode = "200", description = "Emprunt mis à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Emprunt non trouvé"),
            @ApiResponse(responseCode = "400", description = "Données d'emprunt invalides")
        }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Emprunt> updateEmprunt(@PathVariable Long id, @Valid @org.springframework.web.bind.annotation.RequestBody Emprunt empruntDetails) {
        Emprunt updatedEmprunt = empruntService.updateEmprunt(id, empruntDetails);
        return ResponseEntity.ok(updatedEmprunt);
    }

    @Operation(
        summary = "Supprime un emprunt",
        description = "Supprime un emprunt du système",
        responses = {
            @ApiResponse(responseCode = "204", description = "Emprunt supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Emprunt non trouvé")
        }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmprunt(@PathVariable Long id) {
        empruntService.deleteEmprunt(id);
        return ResponseEntity.noContent().build();
    }
}
