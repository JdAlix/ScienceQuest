<?php

namespace model;

class MdlAdmin extends MdlBase{
    private AdminGateway $gw;

    public function __construct(){
        parent::__construct();
        $this->gw = new AdminGateway($this->con);
    }
    public function login(string $username, string $password): bool{
        return $this->gw->login($username, $password);
    }

    public static function isAdmin() {
        if(!isset($_SESSION['admin'])
            || !$_SESSION['admin']
            || !isset($_SESSION['email'])
            || $_SESSION['email'] == null) {
            return false;
        }

        return true;
    }

    public static function logout() {
        global $basePath;

        session_unset();
        session_destroy();
        $_SESSION = array();
        header("Location: .");
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