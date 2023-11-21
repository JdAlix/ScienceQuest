<?php

namespace model;

class MdlUser extends MdlBase{
    private JoueurGateway $gw;

    public function __construct(){
        parent::__construct();
        $this->gw = new UtilisateurConnecteGateway($this->con);
    }
    public function login(string $username, string $password): bool{
        return $this->gw->login($username, $password);
    }
    public function register(string $username, string $password): bool{
        $temp = new JoueurGateway($this->con);
        $temp->insertJoueur($username);
        return $this->gw->register($username, $password, $temp->getFromPseudo($username)["id"]);
    }
/*
    public function setPseudo(int $id, string $pseudo): User{
        $this->gw->setPseudo($id, $pseudo);
        return $this->getFromId($id);
    }

    public function insertInvite(string $pseudo, string $idSession): User{
        $id = $this->gw->insertInvite($pseudo, $idSession);
        return $this->getFromId($id);
    }

    public function getFromId(int $id): User{
        $row = $this->gw->getFromId($id);
        return new Invite($row['idjoueur'], $row['pseudo'], $row['idsession']);
    }*/
}