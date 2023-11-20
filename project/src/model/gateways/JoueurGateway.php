<?php

namespace model;

abstract class JoueurGateway
{
    protected $con;

    function __construct(Connection $con) {
        $this->con = $con;
    }

    protected function getFromPseudo($pseudo): array|bool
    {
        $this->con->executeQuery("SELECT id, pseudo FROM Joueur WHERE pseudo = :pseudo;",
        [":pseudo" => [$pseudo, $this->con::PARAM_STR]]);
        return $this->con->getOneResult();
    }

    protected function insertJoueur(string $pseudo): int{
        if($this->getFromPseudo($pseudo) != false){
            throw new PseudoDejaPrisException();
        }else{
            $this->con->executeQuery("INSERT INTO Joueur(pseudo) VALUES(:pseudo);",
            [":pseudo" => [$pseudo, $this->con::PARAM_STR]]);
            return $this->getFromPseudo($pseudo)["id"];
        }
    }

    public function setPseudo(int $id, string $pseudo){
        if($this->getFromPseudo($pseudo) != false){
            throw new PseudoDejaPrisException();
        }else{
            $this->con->executeQuery("UPDATE Joueur SET pseudo=:pseudo WHERE id=:id",
         [":pseudo" => [$pseudo, $this->con::PARAM_STR],
          ":id" => [$id, $this->con::PARAM_INT]]);
        }
    }
}