<?php

namespace model;

class MdlJeu extends MdlBase{
    private JeuGateway $gw;

    public function __construct(){
        parent::__construct();
        $this->gw = new JeuGateway($this->con);
    }

    public function getAll(): array{
        $listJeu = [];
        foreach($this->gw->getAll() as $row){
            $listJeu[] = new Jeu($row['id'], $row['nom'], $row['nbrparties']);
        }
        return $listJeu;
    }

    public function getFromId(int $id): Jeu{
        $row = $this->gw->getFromId($id);
        return new Jeu($row['id'], $row['nom'], $row['nbrparties']);
    }
}