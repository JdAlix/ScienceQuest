<?php

namespace model;

class InviteGateway extends JoueurGateway
{
    private $con;

    function __construct(Connection $con) {
        $this->con = $con;
    }

    public function getFromIdSession($idSession): array|bool
    {
        $this->con->executeQuery("SELECT id, pseudo, idSession FROM Invite JOIN Joueur ON id=idJoueur WHERE idSession = :idSession;",
        [":idSession" => [$idSession, $this->con::PARAM_STR]]);
        return $this->con->getOneResult();
    }

    public function insertInvite(string $pseudo, string $idSession): int{
        if($this->getFromIdSession($idSession) != false){
            throw new IdSessionDoubleException();
        }else{
            $id = $this->insertJoueur($pseudo);
            $this->con->executeQuery("INSERT INTO Invite(id, idSession) VALUES(:id, :idSession);",
            [":id" => [$id, $this->con::PARAM_INT],
             ":idSession" => [$idSession, $this->con::PARAM_STR]]);
            return $id;
        }
    }
}