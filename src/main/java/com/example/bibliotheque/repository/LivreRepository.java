package com.example.bibliotheque.repository; 
 
import com.example.bibliotheque.model.Livre;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
 
public interface LivreRepository extends JpaRepository<Livre, Long> {
    List<Livre> findByAuteur(String auteur);
    List<Livre> findByAnneePublicationGreaterThan(int annee);
    
    @Query("SELECT l FROM Livre l WHERE l.titre LIKE %:keyword%")
    List<Livre> searchByTitle(@Param("keyword") String keyword);

    @Query("SELECT l FROM Livre l WHERE l.titre LIKE %:keyword%")
    List<Livre> searchByTitle(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT l FROM Livre l WHERE l.auteur = :auteur AND l.anneePublication > :annee")
    List<Livre> findByAuteurAndAfterYear(@Param("auteur") String auteur, @Param("annee") int annee, Pageable pageable);

}