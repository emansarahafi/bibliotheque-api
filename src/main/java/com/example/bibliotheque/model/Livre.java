package com.example.bibliotheque.model; 
 
import jakarta.persistence.*; 
import jakarta.validation.constraints.*;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema; 
 
@Entity
@Data
@Schema(description = "Entité représentant un livre dans le système")
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID auto-généré", example = "1")
    private Long id;

    @NotBlank(message = "{NotBlank.livre.titre}")
    @Size(min = 1, max = 100, message = "{Size.livre.titre}")
    @Schema(description = "Titre du livre", required = true, example = "Le Petit Prince")
    private String titre;

    @NotBlank(message = "{NotBlank.livre.auteur}")
    @Schema(description = "Auteur du livre", required = true, example = "Antoine de Saint-Exupéry")
    private String auteur;

    @Pattern(regexp = "\\d{3}-\\d{10}", message = "{Pattern.livre.isbn}")
    @Schema(description = "ISBN du livre au format XXX-XXXXXXXXXX", required = true, example = "978-2070408504")
    private String isbn;

    @Min(value = 1000, message = "{Min.livre.anneePublication}")
    @Max(value = 2100, message = "{Max.livre.anneePublication}")
    @Schema(description = "Année de publication du livre", required = true, example = "1943")
    private int anneePublication;
}
