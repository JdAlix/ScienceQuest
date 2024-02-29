package fr.iut.sciencequest.sae.services;

import fr.iut.sciencequest.sae.entities.Partie;
import fr.iut.sciencequest.sae.exceptions.notFound.PartieNotFoundException;
import fr.iut.sciencequest.sae.repositories.PartieRepository;
import fr.iut.sciencequest.sae.services.interfaces.IPartieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PartieService implements IPartieService {
    private final PartieRepository partieRepository;
    /*private final JeuRepository jeuRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final ThematiqueRepository thematiqueRepository;
    private final DifficulteRepository difficulteRepository;*/

    public Partie findById(int id) {
        return this.partieRepository.findById(id).orElseThrow(() ->
                new PartieNotFoundException("Partie", id)
        );
    }
    public Partie create(Integer idJeu, Integer idUtilisateur, List<Integer> thematiques, Integer idDifficulte) {
        /*try {
            Jeu jeu = jeuService.findById(idJeu);

            if(partie.getJeu().getNom() == null) {
                throw new MalformedPartyException("Party's game id is not given or is a false value");
            }

            return this.partieRepository.save(partie);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicatedFieldException("ERREUR : il existe déjà une partie : " + partie.getId() + " en base");
        }*/
        return new Partie();
    }
}
