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

    public function getScientifiquesParPage(int $page) : array {
        $nbElemParPage = 20;
        $pageMax = ceil($this->gw->getNbScientifique()/$nbElemParPage);
        if ($page <= 0) {
            $page = 1;
        } elseif ($page > $pageMax) {
            $page = $pageMax;
        }
        $result = $this->gw->getScientifiquesParPages($page,$nbElemParPage);
        $scientifiques = array();
        foreach ($result as $scientifique) {
            $sexe = $this->mdlSexe->getFromId($scientifique['idsexe']);
            $difficulte = $this->mdlDifficulte->getFromId($scientifique['iddifficulte']);
            $thematique = $this->mdlThematique->getFromId($scientifique['idthematique']);
            $scientifiques[] = new Scientifique($scientifique['id'],
                $scientifique['nom'],
                $scientifique['prenom'],
                $scientifique['photo'],
                new DateTime($scientifique['datenaissance']),
                $scientifique['descriptif'],
                $scientifique['ratiotrouvee'],
                $thematique,
                $difficulte,
                $sexe);
        }
        return $scientifiques;
    }

    public function getMaxPages() : int {
        $nbElemParPage = 20;
        return ceil($this->gw->getNbScientifique()/$nbElemParPage);
    }
}