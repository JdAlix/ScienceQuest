<?php

namespace model;

class MdlDifficulte extends MdlBase{
    private DifficulteGateway $gw;

    public function __construct(){
        parent::__construct();
        $this->gw = new DifficulteGateway($this->con);
    }

    public function getAll(): array{
        $listDifficulte = [];
        foreach($this->gw->getAll() as $row){
            $listDifficulte[] = new Difficulte($row['id'], $row['libelle']);
        }
        return $listDifficulte;
    }

    public function getFromId(int $id): Difficulte{
        $row = $this->gw->getFromId($id);
        return new Difficulte($row['id'], $row['libelle']);
    }
}