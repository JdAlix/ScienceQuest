<?php

namespace model;

class ScientifiqueGateway
{
    private $con;

    function __construct(Connection $con) {
        $this->con = $con;
    }

    public function getRandom() {
        $this->con->executeQuery(
            "SELECT id, nom, prenom, photo, dateNaissance, descriptif, ratiotrouvee, idthematique, iddifficulte, idsexe FROM Scientifique ORDER BY RANDOM() LIMIT 1;");
        return $this->con->getOneResult();
    }

    public function addScientifique(Scientifique $sci): bool{
        return $this->con->executeQuery(
            "INSERT INTO Scientifique(nom, prenom, photo, dateNaissance, descriptif, ratioTrouvee, idThematique, idDifficulte, idSexe) VALUES (:nom, :prenom, :photo, :dateNaissance, :descriptif, :ratioTrouvee, :idThematique, :idDifficulte, :idSexe);"
        ,[
            ":nom"=>[$sci->getNom(),$this->con::PARAM_STR],
            ":prenom"=>[$sci->getPrenom(),$this->con::PARAM_STR],
            ":photo"=>[$sci->getPhoto(),$this->con::PARAM_STR],
            ":dateNaissance"=>[date("Y-m-d H:i:s", $sci->getDateNaiss()->getTimestamp()),$this->con::PARAM_STR],
            ":descriptif"=>[$sci->getDescriptif(),$this->con::PARAM_STR],
            ":ratioTrouvee"=>[$sci->getRatioTrouvee(),$this->con::PARAM_STR],
            ":idThematique"=>[$sci->getThematique()->getId(),$this->con::PARAM_STR],
            ":idDifficulte"=>[$sci->getDifficulte()->getId(),$this->con::PARAM_STR],
            ":idSexe"=>[$sci->getSexe()->getId(),$this->con::PARAM_STR]
        ]);
    }
}