package fr.iut.sciencequest.sae.services;

import fr.iut.sciencequest.sae.dto.utilisateur.UtilisateurDTO;
import fr.iut.sciencequest.sae.dto.utilisateur.UtilisateurWithPasswordDTO;
import fr.iut.sciencequest.sae.entities.Utilisateur;
import fr.iut.sciencequest.sae.exceptions.IncorrectPasswordException;
import fr.iut.sciencequest.sae.exceptions.notFound.UtilisateurNotFoundException;
import fr.iut.sciencequest.sae.repositories.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;

@AllArgsConstructor
@Service
public class UtilisateurService {

    private UtilisateurRepository utilisateurRepository;
    //private BCryptPasswordEncoder passwordEncoder;
    private ModelMapper modelMapper;


    public UtilisateurDTO save(UtilisateurWithPasswordDTO user) {
        Utilisateur utilisateur = this.modelMapper.map(user, Utilisateur.class);
        //utilisateur.setMotDePasse(passwordEncoder.encode(user.getMotDePasse()));
        utilisateurRepository.save(utilisateur);
        return this.modelMapper.map(utilisateur, UtilisateurDTO.class);
    }

    public UtilisateurDTO login(UtilisateurWithPasswordDTO user) {
        Utilisateur utilisateur = this.findUserByEmail(user.getEmail());
        if(!utilisateur.getMotDePasse().equals(user.getMotDePasse())) throw new IncorrectPasswordException();
        /*if(!passwordEncoder.matches(user.getMotDePasse(), utilisateur.getMotDePasse())) {
            throw new IncorrectPasswordException();
        }*/

        return this.modelMapper.map(utilisateur, UtilisateurDTO.class);
    }

    public Utilisateur findUserByEmail(String email) {
        Utilisateur user = this.utilisateurRepository.findUtilisateurByEmail(email);
        if(Objects.equals(user.getPseudo(), "")) {
            throw new UtilisateurNotFoundException(email);
        }

        return user;
    }

    public Utilisateur findUserById(int id) {
        return this.utilisateurRepository.findById(id).orElseThrow(() -> new UtilisateurNotFoundException(id));
    }
}
