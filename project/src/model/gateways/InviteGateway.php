<?php

namespace model;

class InviteGateway extends JoueurGateway
{

    function __construct(Connection $con) {
        parent::__construct($con);
    }

//    public function getFromIdSession($idSession): array|bool // <- Seulement autorisé en PHP 8 /!\ (on est en 7.4)
//    {
//        $this->con->executeQuery("SELECT idJoueur, pseudo, idSession FROM Invite JOIN Joueur ON id=idJoueur WHERE idSession = :idSession;",
//        [":idSession" => [$idSession, $this->con::PARAM_STR]]);
//        return $this->con->getOneResult();
//    }

    /**
     * @param string $idSession
     * @return array|bool
     */
    public function getFromIdSession(string $idSession) { // <- PHP 7.4
        $this->con->executeQuery(
            "SELECT idJoueur, pseudo, idSession FROM Invite JOIN Joueur ON id=idJoueur WHERE idSession = :idSession;",
            [":idSession" => [$idSession, $this->con::PARAM_STR]]
        );
        return $this->con->getOneResult();
    }



//    public function getFromId($id): array|bool // <- Seulement autorisé en PHP 8 /!\ (on est en 7.4)
//    {
//        $this->con->executeQuery("SELECT idJoueur, pseudo, idSession FROM Invite JOIN Joueur ON id=idJoueur WHERE id = :id;",
//        [":id" => [$id, $this->con::PARAM_INT]]);
//        return $this->con->getOneResult();
//    }

    /**
     * @param int $id
     * @return array|bool
     */
    public function getFromId(int $id) { // <- PHP 7.4
        $this->con->executeQuery(
            "SELECT idJoueur, pseudo, idSession FROM Invite JOIN Joueur ON id=idJoueur WHERE id = :id;",
            [":id" => [$id, $this->con::PARAM_INT]]
        );
        return $this->con->getOneResult();
    }

    public function insertInvite(string $pseudo, string $idSession): int{
        $row = $this->getFromIdSession($idSession);
        if($row){
            throw new IdSessionDoubleException($row['idjoueur'], $row['idsession']);
        }else{
            $id = $this->insertJoueur($pseudo);
            $this->con->executeQuery("INSERT INTO Invite(idJoueur, idSession) VALUES(:id, :idSession);",
            [":id" => [$id, $this->con::PARAM_INT],
             ":idSession" => [$idSession, $this->con::PARAM_STR]]);
            return $id;
        }
    }
}