<?php

namespace model;

class MdlSexe extends MdlBase{
    private SexeGateway $gw;

    public function __construct(){
        parent::__construct();
        $this->gw = new SexeGateway($this->con);
    }

    public function getFromId(int $id): Sexe{
        $row = $this->gw->getFromId($id);
        return new Sexe($row['id'], $row['libelle']);
    }
}