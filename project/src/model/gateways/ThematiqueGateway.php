<?php

namespace model;

class ThematiqueGateway
{
    private $con;

    function __construct(Connection $con) {
        $this->con = $con;
    }

    public function getFromId(int $id): array
    {
        $this->con->executeQuery("SELECT id, libelle FROM Thematique WHERE id=:id;",
                                [':id' => [$id, $this->con::PARAM_INT]]);
        return $this->con->getOneResult();
    }
    public function getAll(): array
    {
        $this->con->executeQuery("SELECT id, libelle FROM Thematique;");
        return $this->con->getResults();
    }
}