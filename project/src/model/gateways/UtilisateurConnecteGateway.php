<?php

namespace model;

class UtilisateurConnecteGateway extends JoueurGateway {

    function __construct(Connection $con) {
        $this->con = $con;
    }

    public function login(string $email, string $password): bool
    {
        $sql = "SELECT * FROM Utilisateur WHERE email=:email";
        $this->con->executeQuery($sql, array(
            ':email' => array($email, \PDO::PARAM_STR)
        ));

        $result = $this->con->getOneResult();

        if (!empty($result)) {
            return password_verify($password,$result['password']);
        }
        return false;
    }
    public function register(string $email, string $password, int $idjoueur): bool
    {
        $sql = "INSERT INTO Utilisateur(email, password,idjoueur) VALUES (:email,:password,:idjoueur);";
        return $this->con->executeQuery($sql, [
        ':email' => array($email, \PDO::PARAM_STR),
        ':password' => array(password_hash($password, PASSWORD_BCRYPT), \PDO::PARAM_STR),
        ':idjoueur' => array($idjoueur, \PDO::PARAM_INT)
        ]
        );
    }
}