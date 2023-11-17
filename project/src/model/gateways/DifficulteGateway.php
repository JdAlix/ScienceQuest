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

    public function getFromId(int $id): Difficulte
    {
        $this->con->executeQuery("SELECT id, libelle FROM Difficulte WHERE id=:id;",
                                [':id' => [$id, $this->con::PARAM_INT]]);
        $row = $this->con->getOneResult();
        return new Difficulte($row['id'], $row['libelle']);
    }
}