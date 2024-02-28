package fr.iut.sciencequest.sae.services;

import fr.iut.sciencequest.sae.entities.Thematique;
import fr.iut.sciencequest.sae.exceptions.DuplicatedFieldException;
import fr.iut.sciencequest.sae.exceptions.DuplicatedIdException;
import fr.iut.sciencequest.sae.exceptions.notFound.ThematiqueNotFoundException;
import fr.iut.sciencequest.sae.repositories.ThematiqueRepository;
import fr.iut.sciencequest.sae.services.interfaces.IThematiqueService;
import org.springframework.stereotype.Service;

@Service
public class ThematiqueService implements IThematiqueService {

    private final ThematiqueRepository thematiqueRepository;

    public ThematiqueService(ThematiqueRepository thematiqueRepository){
        this.thematiqueRepository = thematiqueRepository;
    }

    private void checkFieldsConstraints(Thematique thematique){
        if(this.thematiqueRepository.existsByLibelle(thematique.getLibelle())){
            throw new DuplicatedFieldException("libelle");
        }
    }
    @Override
    public Thematique update(Thematique thematique){
        if(!this.thematiqueRepository.existsById(thematique.getId())){
            throw new ThematiqueNotFoundException(thematique.getId());
        }
        this.checkFieldsConstraints(thematique);
        return this.thematiqueRepository.save(thematique);
    }

    @Override
    public Thematique create(Thematique thematique){
        if(this.thematiqueRepository.existsById(thematique.getId())){
            throw new DuplicatedIdException();
        }
        this.checkFieldsConstraints(thematique);
        return this.thematiqueRepository.save(thematique);
    }

    @Override
    public Iterable<Thematique> findAll(){
        return this.thematiqueRepository.findAll();
    }

}
