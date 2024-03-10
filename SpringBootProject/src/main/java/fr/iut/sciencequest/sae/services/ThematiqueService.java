package fr.iut.sciencequest.sae.services;

import fr.iut.sciencequest.sae.entities.Thematique;
import fr.iut.sciencequest.sae.exceptions.DuplicatedFieldException;
import fr.iut.sciencequest.sae.exceptions.DuplicatedIdException;
import fr.iut.sciencequest.sae.exceptions.notFound.ThematiqueNotFoundException;
import fr.iut.sciencequest.sae.repositories.ThematiqueRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ThematiqueService {
    private final ThematiqueRepository thematiqueRepository;

    private void checkFieldsConstraints(Thematique thematique){
        if(this.thematiqueRepository.existsByLibelle(thematique.getLibelle())){
            throw new DuplicatedFieldException("libelle");
        }
    }
    public Thematique update(Thematique thematique){
        if(!this.thematiqueRepository.existsById(thematique.getId())){
            throw new ThematiqueNotFoundException(thematique.getId());
        }
        this.checkFieldsConstraints(thematique);
        return this.thematiqueRepository.save(thematique);
    }

    public Thematique create(Thematique thematique){
        if(thematique.getId() != null && this.thematiqueRepository.existsById(thematique.getId())){
            throw new DuplicatedIdException();
        }
        this.checkFieldsConstraints(thematique);
        return this.thematiqueRepository.save(thematique);
    }

    public Page<Thematique> findAll(Pageable p){
        return this.thematiqueRepository.findAll(p);
    }

}
