<?php

namespace model;

class UserGateway
{
    private \PDO $con;
    private \PDOStatement $stmt;
    public function __construct(Connection $con)
    {
        $this->con=$con;
    }

    public function login(string $email, string $motDePasse): bool
    {
        $sql = "SELECT * FROM Utilisateur WHERE email=:email";
        $this->con->executeQuery($sql, array(
            ':email' => array($email, \PDO::PARAM_STR)
        ));

        $result = $this->con->getOneResult();

        if (!empty($result) && password_hash($motDePasse,  PASSWORD_DEFAULT) == $result['motDePasse']) {
            return true;
        }
        return false;
    }
    public function addUser(string $email, string $motDePasse): void
    {
        $sql = "INSERT INTO utilisateur (email, motDePasse) VALUES (:email, :motDePasse)";
        $stmt = $this->con->prepare($sql);
        $stmt->bindValue(':email', $email);
        $stmt->bindValue(':motDePasse', password_hash($motDePasse,  PASSWORD_DEFAULT));
        $stmt->execute();
    }
    public function deleteUser(int $id): void
    {
        $sql = "DELETE FROM utilisateur WHERE id=:id";
        $stmt = $this->con->prepare($sql);
        $stmt->bindValue(':id', $id);
        $stmt->execute();
    }
    public function updateUser(int $id, string $email, string $motDePasse): void
    {
        $sql = "UPDATE utilisateur SET email=:email, motDePasse=:motDePasse WHERE id=:id";
        $stmt = $this->con->prepare($sql);
        $stmt->bindValue(':id', $id);
        $stmt->bindValue(':email', $email);
        $stmt->bindValue(':motDePasse', password_hash($motDePasse,  PASSWORD_DEFAULT));
        $stmt->execute();
    }
    public function getUser(int $id): User
    {
        $sql = "SELECT * FROM utilisateur WHERE id=:id";
        $stmt = $this->con->prepare($sql);
        $stmt->bindValue(':id', $id);
        $stmt->execute();
        $result = $stmt->fetch();
        return new User($result['id'], $result['email'], $result['motDePasse']);
    }
    public function getUsers(): array
    {
        $sql = "SELECT * FROM utilisateur";
        $stmt = $this->con->prepare($sql);
        $stmt->execute();
        $result = $stmt->fetchAll();
        $users = [];
        foreach ($result as $user) {
            $users[] = new User($user['id'], $user['email'], $user['motDePasse']);
        }
        return $users;
    }
    public function getHashedPasswordById(int $id): string
    {
        $sql = "SELECT motDePasse FROM utilisateur WHERE id=:id";
        $stmt = $this->con->prepare($sql);
        $stmt->bindValue(':id', $id);
        $stmt->execute();
        $result = $stmt->fetch();
        return $result['motDePasse'];
    }
    public function getUserId(string $email): int
    {
        $sql = "SELECT id FROM utilisateur WHERE email=:email";
        $stmt = $this->con->prepare($sql);
        $stmt->bindValue(':email', $email);
        $stmt->execute();
        $result = $stmt->fetch();
        return $result['id'];
    }
    public function getUserByEmailAndPassword(string $email, string $motDePasse): User
    {
        $sql = "SELECT * FROM utilisateur WHERE email=:email AND motDePasse=:motDePasse";
        $stmt = $this->con->prepare($sql);
        $stmt->bindValue(':email', $email);
        $stmt->bindValue(':motDePasse', password_hash($motDePasse,  PASSWORD_DEFAULT));
        $stmt->execute();
        $result = $stmt->fetch();
        return new User($result['id'], $result['email'], $result['motDePasse']);
    }
}
