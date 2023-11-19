<?php

namespace model;

class ScientifiqueGateway
{
    private $con;

    function __construct(Connection $con) {
        $this->con = $con;
    }

    public function getRandom(): array|bool{
        $this->con->executeQuery(
            "SELECT id, nom, prenom, photo, dateNaissance, descriptif, ratiotrouvee, idthematique, iddifficulte, idsexe FROM Scientifique ORDER BY RANDOM() LIMIT 1;");
        return $this->con->getOneResult();
    }
}