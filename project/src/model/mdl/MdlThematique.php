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

    public function getAll(): array {
        $ret=array();
        $row = $this->gw->getAll();
        for($i=0; $i< count($row); $i++){
            array_push($ret, new Thematique($row[$i]['id'], $row[$i]['libelle']));
        }
        return $ret;
    }
}