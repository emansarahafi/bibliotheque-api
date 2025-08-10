package com.example.bibliotheque.dto;

import lombok.Data;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
public class EmpruntDTO {
    
    @NotNull(message = "{NotNull.empruntDTO.dateEmprunt}")
    @PastOrPresent(message = "{PastOrPresent.empruntDTO.dateEmprunt}")
    private LocalDate dateEmprunt;

    @NotNull(message = "{NotNull.empruntDTO.dateRetour}")
    @Future(message = "{Future.empruntDTO.dateRetour}")
    private LocalDate dateRetour;

    @NotBlank(message = "{NotBlank.empruntDTO.statut}")
    @Pattern(regexp = "EN_COURS|TERMINE|RETARD", message = "{Pattern.empruntDTO.statut}")
    private String statut;

    @NotNull(message = "{NotNull.empruntDTO.livreId}")
    private Long livreId;

    @NotBlank(message = "{NotBlank.empruntDTO.emprunteur}")
    @Size(min = 2, max = 100, message = "{Size.empruntDTO.emprunteur}")
    private String emprunteur;
}
