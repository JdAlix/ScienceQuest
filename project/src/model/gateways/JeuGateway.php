<?php

namespace model;

use PDO;

class JeuGateway
{
    private $con;

    function __construct(Connection $con) {
        $this->con = $con;
    }
    public function getAll() : array
    {
        $this->con->executeQuery("SELECT id, nom, nbrparties FROM Jeu;");
        $listJeu = [];
        foreach($this->con->getResults() as $row){
            $listJeu[] = new Jeu($row['id'], $row['nom'], $row['nbrparties']);
        }
        return $listJeu;
    }

    public function getFromId(int $id): Jeu
    {
        $this->con->executeQuery("SELECT id, nom, nbrparties FROM Jeu WHERE id=:id;",
                                [':id' => [$id, $this->con::PARAM_INT]]);
        $row = $this->con->getOneResult();
        return new Jeu($row['id'], $row['nom'], $row['nbrparties']);
    }
}