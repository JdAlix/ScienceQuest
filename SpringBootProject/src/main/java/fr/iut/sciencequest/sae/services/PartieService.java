package fr.iut.sciencequest.sae.services;

import fr.iut.sciencequest.sae.entities.*;
import fr.iut.sciencequest.sae.exceptions.DuplicatedIdException;
import fr.iut.sciencequest.sae.exceptions.MalformedPartyException;
import fr.iut.sciencequest.sae.exceptions.notFound.JeuNotFoundException;
import fr.iut.sciencequest.sae.exceptions.notFound.PartieNotFoundException;
import fr.iut.sciencequest.sae.exceptions.notFound.ThematiqueNotFoundException;
import fr.iut.sciencequest.sae.repositories.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class PartieService {
    private final PartieRepository partieRepository;
    private final JeuRepository jeuRepository;
    private final JoueurRepository joueurRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final ThematiqueRepository thematiqueRepository;
    private final DifficulteRepository difficulteRepository;
    private final JoueurService joueurService;

    @PersistenceContext
    private EntityManager entityManager;

    public Partie findById(int id) {
        return this.partieRepository.findById(id).orElseThrow(() ->
                new PartieNotFoundException(id)
        );
    }

    public Partie update(Partie partie){
        if(!this.partieRepository.existsById(partie.getId())){
            throw new PartieNotFoundException(partie.getId());
        }
        Partie savedPartie = this.partieRepository.save(partie);
        return this.findById(savedPartie.getId());
    }

    public Partie create(Partie partie){
        if(partie.getId() != null && this.partieRepository.existsById(partie.getId())){
            throw new DuplicatedIdException();
        }
        Partie savedPartie = this.partieRepository.save(partie);
        return this.findById(savedPartie.getId());
    }

}
