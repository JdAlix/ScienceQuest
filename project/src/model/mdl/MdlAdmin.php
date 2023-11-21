<?php

namespace model;

class MdlAdmin extends MdlBase{
    private AdminGateway $gw;

    public function __construct(){
        parent::__construct();
        $this->gw = new AdminGateway($this->con);
    }
    public function login(string $username, string $password): bool{
        if ($this->gw->login($username, $password)) {
            $_SESSION['pseudo'] = $username;
            $_SESSION['admin'] = true;
            return true;
        }
        return false;
    }

    public static function isAdmin(): bool
    {
        if(!isset($_SESSION['admin'])
            || !$_SESSION['admin']
            || !isset($_SESSION['pseudo'])
            || $_SESSION['pseudo'] == null) {
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

    public function getFromEmail(string $email): Admin{
        $row = $this->gw->getFromEmail($email);
        return new Admin($row['id'], $row['email'], $row['password']);
    }
}