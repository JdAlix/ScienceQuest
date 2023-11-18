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
        return $this->con->getResults();
    }

    public function getFromId(int $id): array
    {
        $this->con->executeQuery("SELECT id, nom, nbrparties FROM Jeu WHERE id=:id;",
                                [':id' => [$id, $this->con::PARAM_INT]]);
        return $this->con->getOneResult();
    }
}