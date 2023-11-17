<?php

namespace model;

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
}