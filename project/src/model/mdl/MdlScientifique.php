<?php

namespace model;

use DateTime;
use Exception;
use RuntimeException;

class MdlScientifique extends MdlBase{
    private ScientifiqueGateway $gw;
    private MdlSexe $mdlSexe;
    private MdlDifficulte $mdlDifficulte;
    private MdlThematique $mdlThematique;

    public function __construct(){
        parent::__construct();
        $this->gw = new ScientifiqueGateway($this->con);
        $this->mdlSexe = new MdlSexe();
        $this->mdlDifficulte = new MdlDifficulte();
        $this->mdlThematique = new MdlThematique();
    }

    /**
     * @throws Exception
     */
    public function getRandom(): Scientifique{
        $row = $this->gw->getRandom();
        if(!$row) throw new RuntimeException("Erreur aucun scientifique trouvé");
        $sexe = $this->mdlSexe->getFromId($row['idsexe']);
        $difficulte = $this->mdlDifficulte->getFromId($row['iddifficulte']);
        $thematique = $this->mdlThematique->getFromId($row['idthematique']);

        return new Scientifique($row['id'],
                                $row['nom'],
                                $row['prenom'],
                                $row['photo'],
                                new DateTime($row['datenaissance']),
                                $row['descriptif'],
                                $row['ratiotrouvee'],
                                $thematique,
                                $difficulte,
                                $sexe);
    }
    public function addScientifique(Scientifique $s){
		return $this->gw->addScientifique($s);
	}
    public function editScientifique(Scientifique $s){
		return $this->gw->editScientifique($s);
	}

    public function getScientifique(int $id){
        $t=$this->gw->getScientifique($id);
        if(gettype($t)!="array"){
            throw new Exception("Scientifique non trouvé");
        }

        $sexe=new MdlSexe();
        $sexe=$sexe->getFromId($t["idsexe"]);

        $diff=new MdlDifficulte();
        $diff=$diff->getFromId($t["iddifficulte"]);

        $theme=new MdlThematique();
        $theme=$theme->getFromId($t["idthematique"]);

        return new Scientifique(
            $id,
            $t["nom"],
            $t["prenom"],
            $t["photo"],
            DateTime::createFromFormat("Y-m-d", $t["datenaissance"]),
            $t["descriptif"],
            $t["ratiotrouvee"],
            $theme,
            $diff,
            $sexe
        );
        

	}
}