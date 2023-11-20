<?php

namespace model;

class MdlThematique extends MdlBase{
    private ThematiqueGateway $gw;

    public function __construct(){
        parent::__construct();
        $this->gw = new ThematiqueGateway($this->con);
    }

    public function getFromId(int $id): Thematique{
        $row = $this->gw->getFromId($id);
        return new Thematique($row['id'], $row['libelle']);
    }
}