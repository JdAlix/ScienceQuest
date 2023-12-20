<?php

namespace model;

class PartieGateway
{
    public function __construct(Connection $con)
    {

    }

    public function getFromId(int $id) : Partie {

    }

    public function getFromCodeInvitation(string $code) : Partie {

    }

    public function creerPartie(Jeu $jeu, Joueur $joueur) : Partie {

    }

    public function rejoindrePartie(string $codeInvitation, Joueur $joueur) : bool {

    }

    public function supprimerPartie(int $id) : bool {

    }
}