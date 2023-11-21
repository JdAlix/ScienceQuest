<?php

namespace model;

class ScientifiqueGateway
{
    private Connection $con;

    function __construct(Connection $con) {
        $this->con = $con;
    }

//    public function getRandom(): array|bool{ <- autorisé seulement en PHP 8
//        $this->con->executeQuery(
//            "SELECT id, nom, prenom, photo, dateNaissance, descriptif, ratiotrouvee, idthematique, iddifficulte, index FROM Scientifique ORDER BY RANDOM() LIMIT 1;");
//        return $this->con->getOneResult();
//    }

    /**
     * @return array|bool
     */
    public function getRandom() { // PHP 7.4
        $this->con->executeQuery(
            "SELECT id, nom, prenom, photo, dateNaissance, descriptif, ratiotrouvee, idthematique, iddifficulte, idsexe FROM Scientifique ORDER BY RANDOM() LIMIT 1;"
        );
        return $this->con->getOneResult();
    }
}