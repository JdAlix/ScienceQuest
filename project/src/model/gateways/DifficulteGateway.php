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
        return $this->con->getResults();
    }

    public function getFromId(int $id): array
    {
        $this->con->executeQuery("SELECT id, libelle FROM Difficulte WHERE id=:id;",
                                [':id' => [$id, $this->con::PARAM_INT]]);
        return $this->con->getOneResult();
    }
}