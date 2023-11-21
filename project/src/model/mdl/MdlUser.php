<?php

namespace model;

class MdlUser extends MdlBase{
    private UtilisateurConnecteGateway $gw;

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
    
    public function setPseudo(int $id, string $pseudo): Utilisateur{
        $this->gw->setPseudo($id, $pseudo);
        return $this->getFromId($id);
    }

    public function getFromId(int $id): Utilisateur{
        $row = $this->gw->getFromId($id);
        return new Utilisateur($row['idjoueur'], $row['pseudo'], $row['email'], $row['password']);
    }

    public function getFromEmail(string $email): Utilisateur{
        $row = $this->gw->getFromEmail($email);
        return new Utilisateur($row['idjoueur'], $row['pseudo'], $row['email'], $row['password']);
    }

    public function addScientifiqueDecouvert(int $idUtilisateur, int $idScientifique){
        $this->gw->addScientifiqueDecouvert($idUtilisateur, $idScientifique);
    }
}