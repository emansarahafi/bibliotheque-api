package com.example.bibliotheque.service; 
 
import com.example.bibliotheque.model.Livre; 
import com.example.bibliotheque.repository.LivreRepository; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
 
import java.util.List; 
 
@Service 
public class LivreService { 
    @Autowired 
    private LivreRepository livreRepository; 
 
    public List<Livre> getAllLivres() { 
        return livreRepository.findAll(); 
    } 
 
    public Livre getLivreById(Long id) { 
        return livreRepository.findById(id).orElse(null); 
    } 
 
    public Livre saveLivre(Livre livre) { 
        return livreRepository.save(livre); 
    } 
 
    public void deleteLivre(Long id) { 
        livreRepository.deleteById(id); 
    } 

    public Livre updateLivre(Long id, Livre livreDetails) { 
        Livre livre = livreRepository.findById(id).orElse(null); 
        if (livre != null) { 
            livre.setTitre(livreDetails.getTitre()); 
            livre.setAuteur(livreDetails.getAuteur()); 
            livre.setAnneePublication(livreDetails.getAnneePublication()); 
            return livreRepository.save(livre); 
        } 
        return null; 
    } 

}