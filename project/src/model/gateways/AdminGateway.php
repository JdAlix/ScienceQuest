<?php

namespace model;

use PDO;
use PDOStatement;

class AdminGateway
{
    private Connection $con;
    public function __construct(Connection $con)
    {
        $this->con=$con;
    }

    public function login(string $email, string $password): bool
    {
        $sql = "SELECT * FROM Admin WHERE email=:email";
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
        $sql = "INSERT INTO Admin (email, password) VALUES (:email, :password)";
        $stmt = $this->con->prepare($sql);
        $stmt->bindValue(':email', $email);
        $stmt->bindValue(':password', password_hash($password,  PASSWORD_DEFAULT));
        $stmt->execute();
    }
    public function deleteUser(int $id): void
    {
        $sql = "DELETE FROM Admin WHERE id=:id";
        $stmt = $this->con->prepare($sql);
        $stmt->bindValue(':id', $id);
        $stmt->execute();
    }
    public function updateUser(int $id, string $email, string $password): void
    {
        $sql = "UPDATE Admin SET email=:email, password=:password WHERE id=:id";
        $stmt = $this->con->prepare($sql);
        $stmt->bindValue(':id', $id);
        $stmt->bindValue(':email', $email);
        $stmt->bindValue(':password', password_hash($password,  PASSWORD_DEFAULT));
        $stmt->execute();
    }
    public function getUser(int $id): Admin
    {
        $sql = "SELECT * FROM Admin WHERE id=:id";
        $stmt = $this->con->prepare($sql);
        $stmt->bindValue(':id', $id);
        $stmt->execute();
        $result = $stmt->fetch();
        return new Admin($result['id'], $result['email'], $result['password']);
    }
    public function getUsers(): array
    {
        $sql = "SELECT * FROM Admin";
        $stmt = $this->con->prepare($sql);
        $stmt->execute();
        $result = $stmt->fetchAll();
        $users = [];
        foreach ($result as $user) {
            $users[] = new Admin($user['id'], $user['email'], $user['password']);
        }
        return $users;
    }
    public function getHashedPasswordById(int $id): string
    {
        $sql = "SELECT password FROM Admin WHERE id=:id";
        $stmt = $this->con->prepare($sql);
        $stmt->bindValue(':id', $id);
        $stmt->execute();
        $result = $stmt->fetch();
        return $result['password'];
    }
    public function getHashedPassword(int $email): string
    {
        $sql = "SELECT password FROM Admin WHERE email=:email";
        $stmt = $this->con->prepare($sql);
        $stmt->bindValue(':email', $email);
        $stmt->execute();
        $result = $stmt->fetch();
        return $result['password'];
    }

    public function getUserId(string $email): int
    {
        $sql = "SELECT id FROM Admin WHERE email=:email";
        $stmt = $this->con->prepare($sql);
        $stmt->bindValue(':email', $email);
        $stmt->execute();
        $result = $stmt->fetch();
        return $result['id'];
    }
    public function getUserByEmailAndPassword(string $email, string $password): Admin
    {
        $sql = "SELECT * FROM Admin WHERE email=:email AND password=:password";
        $stmt = $this->con->prepare($sql);
        $stmt->bindValue(':email', $email);
        $stmt->bindValue(':password', password_hash($password,  PASSWORD_DEFAULT));
        $stmt->execute();
        $result = $stmt->fetch();
        return new Admin($result['id'], $result['email'], $result['password']);
    }

    public function getFromEmail(string $email): array
    {
        $this->con->executeQuery(
            "SELECT id, email, password FROM Admin WHERE email = :email;",
            [":pseudo" => [$email, $this->con::PARAM_STR]]
        );
        return $this->con->getOneResult();
    }
}
