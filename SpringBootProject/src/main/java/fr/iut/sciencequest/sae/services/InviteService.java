package fr.iut.sciencequest.sae.services;

import fr.iut.sciencequest.sae.entities.Joueur;
import fr.iut.sciencequest.sae.entities.Partie;
import fr.iut.sciencequest.sae.exceptions.partie.PartyAlreadyStartedException;
import fr.iut.sciencequest.sae.exceptions.notFound.InviteNotFoundException;
import fr.iut.sciencequest.sae.repositories.JoueurRepository;
import fr.iut.sciencequest.sae.repositories.PartieRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@AllArgsConstructor
@Service
public class InviteService {
}
