<?php

namespace model;

class DifficulteGateway
{
    private $con;

    function __construct(Connection $con) {
        $this->con = $con;
    }
    public function getAll() : array
    {
        $this->con->executeQuery("SELECT id, libelle FROM Difficulte;");
        $listDifficulte = [];
        foreach($this->con->getResults() as $row){
            $listDifficulte[] = new Difficulte($row['id'], $row['libelle']);
        }
        return $listDifficulte;
    }
}