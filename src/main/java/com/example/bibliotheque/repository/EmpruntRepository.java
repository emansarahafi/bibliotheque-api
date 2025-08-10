package com.example.bibliotheque.repository;

import com.example.bibliotheque.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
    
    List<Emprunt> findByStatut(String statut);
    
    List<Emprunt> findByEmprunteur(String emprunteur);
    
    @Query("SELECT e FROM Emprunt e WHERE e.dateRetour < :date AND e.statut = 'EN_COURS'")
    List<Emprunt> findEmpruntsEnRetard(@Param("date") LocalDate date);
}
