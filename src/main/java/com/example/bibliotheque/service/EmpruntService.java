package com.example.bibliotheque.service;

import com.example.bibliotheque.model.Emprunt;
import com.example.bibliotheque.repository.EmpruntRepository;
import com.example.bibliotheque.exception.BusinessException;
import com.example.bibliotheque.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmpruntService {
    
    @Autowired
    private EmpruntRepository empruntRepository;

    public List<Emprunt> getAllEmprunts() {
        return empruntRepository.findAll();
    }

    public Emprunt getEmpruntById(Long id) {
        return empruntRepository.findById(id)
            .orElseThrow(() -> new BusinessException(
                ErrorCode.LIVRE_NOT_FOUND, 
                "Emprunt with ID " + id + " not found"
            ));
    }

    public Emprunt saveEmprunt(Emprunt emprunt) {
        // Validation métier supplémentaire
        if (emprunt.getDateRetour().isBefore(emprunt.getDateEmprunt())) {
            throw new IllegalArgumentException("La date de retour ne peut pas être antérieure à la date d'emprunt");
        }
        return empruntRepository.save(emprunt);
    }

    public void deleteEmprunt(Long id) {
        empruntRepository.deleteById(id);
    }

    public Emprunt updateEmprunt(Long id, Emprunt empruntDetails) {
        Emprunt emprunt = getEmpruntById(id);
        emprunt.setDateEmprunt(empruntDetails.getDateEmprunt());
        emprunt.setDateRetour(empruntDetails.getDateRetour());
        emprunt.setStatut(empruntDetails.getStatut());
        emprunt.setLivre(empruntDetails.getLivre());
        emprunt.setEmprunteur(empruntDetails.getEmprunteur());
        return empruntRepository.save(emprunt);
    }

    public List<Emprunt> getEmpruntsEnRetard() {
        return empruntRepository.findEmpruntsEnRetard(LocalDate.now());
    }
}
