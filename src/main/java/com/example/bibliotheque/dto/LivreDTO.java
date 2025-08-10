package com.example.bibliotheque.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Data
public class LivreDTO {
    @NotBlank(message = "{NotBlank.livreDTO.titre}")
    private String titre;
    
    @Pattern(regexp = "\\d{3}-\\d{10}", message = "{Pattern.livreDTO.isbn}")
    private String isbn;
}
