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
}