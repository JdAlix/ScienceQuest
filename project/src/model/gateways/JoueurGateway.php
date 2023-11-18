<?php

namespace model;

abstract class JoueurGateway
{
    private $con;

    function __construct(Connection $con) {
        $this->con = $con;
    }

    public function getFromPseudo($pseudo): array|bool
    {
        $this->con->executeQuery("SELECT id, pseudo FROM Joueur WHERE pseudo = :pseudo;",
        [":pseudo" => [$pseudo, $this->con::PARAM_STR]]);
        return $this->con->getOneResult();
    }

    public function insertJoueur(string $pseudo): int{
        if($this->getFromPseudo($pseudo) != false){
            throw new PseudoDejaPrisException();
        }else{
            $this->con->executeQuery("INSERT INTO Joueur(pseudo) VALUES(:pseudo);",
            [":pseudo" => [$pseudo, $this->con::PARAM_STR]]);
            return $this->getFromPseudo($pseudo)["id"];
        }
    }
}