<?php

namespace model;

use Exception;

class ScientifiqueGateway
{
    private Connection $con;

    function __construct(Connection $con) {
        $this->con = $con;
    }

    public function getFromId(int $id) : array {

    }

//    public function getRandom(): array|bool{ <- autorisé seulement en PHP 8
//        $this->con->executeQuery(
//            "SELECT id, nom, prenom, photo, dateNaissance, descriptif, ratiotrouvee, idthematique, iddifficulte, index FROM Scientifique ORDER BY RANDOM() LIMIT 1;");
//        return $this->con->getOneResult();
//    }

    /**
     * @return array|bool
     * @throws Exception
     */
    public function getRandom() { // PHP 7.4
        $this->con->executeQuery(
            "SELECT id, nom, prenom, photo, dateNaissance, descriptif, ratiotrouvee, idthematique, iddifficulte, idsexe FROM Scientifique ORDER BY RANDOM() LIMIT 1;"
        );
        return $this->con->getOneResult();
    }

    public function getRandomFromDifficulte(Difficulte $difficulte) : array {

    }

    /**
     * @throws Exception
     */
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
        ]) ? true : false ;
    }

    public function getScientifiquesParPages(int $currentPage, int $nbElemByPage) : array {
        $query = 'SELECT * FROM Scientifique LIMIT :nbElem OFFSET :ind ';
        $index = ($currentPage-1)*$nbElemByPage;
        $this->con->executeQuery($query,array(
            ':ind' => array($index,\PDO::PARAM_INT),
            ':nbElem' => array($nbElemByPage,\PDO::PARAM_INT)
        ));
        return $this->con->getResults();
    }

    public function getHistorique(string $pseudoJoueur, int $currentPage, int $nbElemByPage) : array {
        if($currentPage == 0) $currentPage = 1;

        $query = 'SELECT s.*
                  FROM Scientifique s, Decouvrir d, Utilisateur u
                  WHERE s.id = d.idscientifique
                    AND d.idutilisateur = u.idJoueur
                    AND u.email = :pseudo
                  LIMIT :nbElem OFFSET :ind ';
        $index = ($currentPage-1)*$nbElemByPage;
        $this->con->executeQuery($query,array(
            ':ind' => array($index,\PDO::PARAM_INT),
            ':nbElem' => array($nbElemByPage,\PDO::PARAM_INT),
            ':pseudo' => array($pseudoJoueur,\PDO::PARAM_STR)
        ));
        return $this->con->getResults();
    }

    public function getNbScientifique() : int {
        $query = 'SELECT DISTINCT count(*) as val FROM Scientifique';
        $this->con->executeQuery($query);
        return $this->con->getResults()[0]['val'];
    }

    public function getNbScientifiqueHistorique(string $pseudoJoueur) : int {
        $query = 'SELECT count(*) as val
                  FROM Scientifique s, Decouvrir d, Utilisateur u
                  WHERE s.id = d.idscientifique
                    AND d.idutilisateur = u.idJoueur
                    AND u.email = :pseudo';
        $params = array(
            ':pseudo' => array($pseudoJoueur,\PDO::PARAM_STR)
        );
        $this->con->executeQuery($query,$params);
        return $this->con->getResults()[0]['val'];
    }

    /**
     * @throws Exception
     */
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
        ]) ? true : false ;
    }

    /**
     * @throws Exception
     */
    public function deleteScientifique(int $id): bool{
        return $this->con->executeQuery(
            "DELETE FROM Scientifique WHERE id=:id;"
        ,[
            ":id"=>[$id,$this->con::PARAM_INT]
        ]);
    }

    /**
     * @throws Exception
     */
    public function getScientifique(int $id) {
        $this->con->executeQuery(
            "SELECT id, nom, prenom, photo, dateNaissance, descriptif, ratioTrouvee, idThematique, idDifficulte, idSexe FROM Scientifique WHERE id=:id;"
        ,[
            ":id"=>[$id,$this->con::PARAM_INT]
        ]);
        return $this->con->getOneResult();
    }

    /**
     * @throws Exception
     */
    public function getQuestions(int $idScientifique): array
    {
        $query = "SELECT q.* FROM Question q
          JOIN Reponse r ON q.id = r.idQuestion
          WHERE r.idScientifique = :idScientifique
          ORDER BY RANDOM() LIMIT 5";
        $params = [":idScientifique" => [$idScientifique, $this->con::PARAM_INT]];

        $this->con->executeQuery($query, $params);

        return $this->con->getResults();
    }
}