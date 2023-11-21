<?php

namespace model;

class ScientifiqueGateway
{
    private Connection $con;

    function __construct(Connection $con) {
        $this->con = $con;
    }

//    public function getRandom(): array|bool{ <- autorisé seulement en PHP 8
//        $this->con->executeQuery(
//            "SELECT id, nom, prenom, photo, dateNaissance, descriptif, ratiotrouvee, idthematique, iddifficulte, index FROM Scientifique ORDER BY RANDOM() LIMIT 1;");
//        return $this->con->getOneResult();
//    }

    /**
     * @return array|bool
     */
    public function getRandom() { // PHP 7.4
        $this->con->executeQuery(
            "SELECT id, nom, prenom, photo, dateNaissance, descriptif, ratiotrouvee, idthematique, iddifficulte, idsexe FROM Scientifique ORDER BY RANDOM() LIMIT 1;"
        );
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
    public function editScientifique(Scientifique $sci): bool{
        return $this->con->executeQuery(
            "UPDATE Scientifique SET nom = :nom, prenom = :prenom, photo = :photo, dateNaissance = :dateNaissance, descriptif = :descriptif, ratioTrouvee = :ratioTrouvee, idThematique = :idThematique, idDifficulte = :idDifficulte, idSexe = :idSexe WHERE id=:id;"
        ,[
            ":nom"=>[$sci->getNom(),$this->con::PARAM_STR],
            ":prenom"=>[$sci->getPrenom(),$this->con::PARAM_STR],
            ":photo"=>[$sci->getPhoto(),$this->con::PARAM_STR],
            ":dateNaissance"=>[date("Y-m-d H:i:s", $sci->getDateNaiss()->getTimestamp()),$this->con::PARAM_STR],
            ":descriptif"=>[$sci->getDescriptif(),$this->con::PARAM_STR],
            ":ratioTrouvee"=>[$sci->getRatioTrouvee(),$this->con::PARAM_STR],
            ":idThematique"=>[$sci->getThematique()->getId(),$this->con::PARAM_STR],
            ":idDifficulte"=>[$sci->getDifficulte()->getId(),$this->con::PARAM_STR],
            ":idSexe"=>[$sci->getSexe()->getId(),$this->con::PARAM_STR],
            ":id"=>[$sci->getId(),$this->con::PARAM_INT]
        ]);
    }

    public function getScientifique(int $id): b {
        $this->con->executeQuery(
            "SELECT id, nom, prenom, photo, dateNaissance, descriptif, ratioTrouvee, idThematique, idDifficulte, idSexe FROM Scientifique WHERE id=:id;"
        ,[
            ":id"=>[$id,$this->con::PARAM_INT]
        ]);
        return $this->con->getOneResult();
    }
}