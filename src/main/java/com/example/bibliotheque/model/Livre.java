package com.example.bibliotheque.model; 
 
import jakarta.persistence.*; 
import lombok.Data; 
import lombok.NoArgsConstructor; 
 
@Entity 
@Data 
@NoArgsConstructor 
public class Livre { 
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id; 
 
    @Column(nullable = false) 
    private String titre; 
 
    @Column(nullable = false) 
    private String auteur; 
 
    private int anneePublication; 
}