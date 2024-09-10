package fr.cnieg.formation.formationback.repository;

import fr.cnieg.formation.formationback.model.Avis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AvisRepository extends JpaRepository<Avis, UUID> {

    List<Avis> findAll();
    List<Avis> findAvisByFormationContainingIgnoreCase(String formation);
    List<Avis> findAvisByOrganismeContainingIgnoreCase(String organisme);
}
