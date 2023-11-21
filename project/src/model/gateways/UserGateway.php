<?php

namespace model;

use PDO;
use PDOStatement;

class UserGateway
{
    private Connection $con;
    private PDOStatement $stmt;
    public function __construct(Connection $con)
    {
        $this->con=$con;
    }

    public function login(string $email, string $password): bool
    {
        $sql = "SELECT * FROM Utilisateur WHERE email=:email";
        $this->con->executeQuery($sql, array(
            ':email' => array($email, PDO::PARAM_STR)
        ));

        $result = $this->con->getOneResult();
        
        if (!empty($result)) {
            return password_verify($password,$result['password']);
        }
        return false;
    }
    public function addUser(string $email, string $password): void
    {
        $sql = "INSERT INTO Utilisateur (email, password) VALUES (:email, :password)";
        $stmt = $this->con->prepare($sql);
        $stmt->bindValue(':email', $email);
        $stmt->bindValue(':password', password_hash($password,  PASSWORD_DEFAULT));
        $stmt->execute();
    }
    public function deleteUser(int $id): void
    {
        $sql = "DELETE FROM Utilisateur WHERE idJoueur=:id";
        $stmt = $this->con->prepare($sql);
        $stmt->bindValue(':id', $id);
        $stmt->execute();
    }
    public function updateUser(int $id, string $email, string $password): void
    {
        $sql = "UPDATE Utilisateur SET email=:email, password=:password WHERE idJoueur=:id";
        $stmt = $this->con->prepare($sql);
        $stmt->bindValue(':id', $id);
        $stmt->bindValue(':email', $email);
        $stmt->bindValue(':password', password_hash($password,  PASSWORD_DEFAULT));
        $stmt->execute();
    }
    public function getUser(int $id): User
    {
        $sql = "SELECT * FROM Utilisateur WHERE idJoueur=:id";
        $stmt = $this->con->prepare($sql);
        $stmt->bindValue(':id', $id);
        $stmt->execute();
        $result = $stmt->fetch();
        return new User($result['id'], $result['email'], $result['password']);
    }
    public function getUsers(): array
    {
        $sql = "SELECT * FROM utilisateur";
        $stmt = $this->con->prepare($sql);
        $stmt->execute();
        $result = $stmt->fetchAll();
        $users = [];
        foreach ($result as $user) {
            $users[] = new User($user['id'], $user['email'], $user['password']);
        }
        return $users;
    }
    public function getHashedPasswordById(int $id): string
    {
        $sql = "SELECT password FROM Utilisateur WHERE idJoueur=:id";
        $stmt = $this->con->prepare($sql);
        $stmt->bindValue(':id', $id);
        $stmt->execute();
        $result = $stmt->fetch();
        return $result['password'];
    }
    public function getHashedPassword(int $email): string
    {
        $sql = "SELECT password FROM Utilisateur WHERE email=:email";
        $stmt = $this->con->prepare($sql);
        $stmt->bindValue(':email', $email);
        $stmt->execute();
        $result = $stmt->fetch();
        return $result['password'];
    }
    public function getUserId(string $email): int
    {
        $sql = "SELECT idJoueur FROM Utilisateur WHERE email=:email";
        $stmt = $this->con->prepare($sql);
        $stmt->bindValue(':email', $email);
        $stmt->execute();
        $result = $stmt->fetch();
        return $result['id'];
    }
    public function getUserByEmailAndPassword(string $email, string $password): User
    {
        $sql = "SELECT * FROM utilisateur WHERE email=:email AND password=:password";
        $stmt = $this->con->prepare($sql);
        $stmt->bindValue(':email', $email);
        $stmt->bindValue(':password', password_hash($password,  PASSWORD_DEFAULT));
        $stmt->execute();
        $result = $stmt->fetch();
        return new User($result['id'], $result['email'], $result['password']);
    }
}
