package com.example.bibliotheque.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Entity
@Data
@Schema(description = "Entité représentant un emprunt de livre")
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID auto-généré de l'emprunt", example = "1")
    private Long id;

    @NotNull(message = "{NotNull.emprunt.dateEmprunt}")
    @PastOrPresent(message = "{PastOrPresent.emprunt.dateEmprunt}")
    @Schema(description = "Date à laquelle le livre a été emprunté", required = true, example = "2025-08-10")
    private LocalDate dateEmprunt;

    @NotNull(message = "{NotNull.emprunt.dateRetour}")
    @Future(message = "{Future.emprunt.dateRetour}")
    @Schema(description = "Date prévue de retour du livre", required = true, example = "2025-08-24")
    private LocalDate dateRetour;

    @NotBlank(message = "{NotBlank.emprunt.statut}")
    @Pattern(regexp = "EN_COURS|TERMINE|RETARD", message = "{Pattern.emprunt.statut}")
    @Schema(description = "Statut de l'emprunt", required = true, example = "EN_COURS", allowableValues = {"EN_COURS", "TERMINE", "RETARD"})
    private String statut;

    @ManyToOne
    @JoinColumn(name = "livre_id")
    @NotNull(message = "{NotNull.emprunt.livre}")
    @Schema(description = "Livre emprunté", required = true)
    private Livre livre;

    @NotBlank(message = "{NotBlank.emprunt.emprunteur}")
    @Schema(description = "Nom de la personne qui emprunte le livre", required = true, example = "Jean Dupont")
    private String emprunteur;
}
