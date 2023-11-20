<?php

namespace model;

class SexeGateway
{
    private $con;

    function __construct(Connection $con) {
        $this->con = $con;
    }

    public function getFromId(int $id): array
    {
        $this->con->executeQuery("SELECT id, libelle FROM Sexe WHERE id=:id;",
                                [':id' => [$id, $this->con::PARAM_INT]]);
        return $this->con->getOneResult();
    }
    
    public function getAll(): array
    {
        $this->con->executeQuery("SELECT id, libelle FROM Sexe;");
        return $this->con->getResults();
    }
}