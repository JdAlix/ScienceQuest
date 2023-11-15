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

    public function login(string $username, string $password): bool
    {
        $sql = "SELECT * FROM user WHERE username=:username";
        $this->con->executeQuery($sql, array(
            ':username' => array($username, \PDO::PARAM_STR)
        ));

        $result = $this->con->getOneResult();

        if (!empty($result) && md5($password) == $result['password']) {
            return true;
        }
        return false;
    }
    public function addUser(string $username, string $password): void
    {
        $sql = "INSERT INTO user (username, password) VALUES (:username, :password)";
        $stmt = $this->con->prepare($sql);
        $stmt->bindValue(':username', $username);
        $stmt->bindValue(':password', password_hash($password, 'md5'));
        $stmt->execute();
    }
    public function deleteUser(int $id): void
    {
        $sql = "DELETE FROM user WHERE id=:id";
        $stmt = $this->con->prepare($sql);
        $stmt->bindValue(':id', $id);
        $stmt->execute();
    }
    public function updateUser(int $id, string $username, string $password): void
    {
        $sql = "UPDATE user SET username=:username, password=:password WHERE id=:id";
        $stmt = $this->con->prepare($sql);
        $stmt->bindValue(':id', $id);
        $stmt->bindValue(':username', $username);
        $stmt->bindValue(':password', $password);
        $stmt->execute();
    }
    public function getUser(int $id): User
    {
        $sql = "SELECT * FROM user WHERE id=:id";
        $stmt = $this->con->prepare($sql);
        $stmt->bindValue(':id', $id);
        $stmt->execute();
        $result = $stmt->fetch();
        return new User($result['id'], $result['username'], $result['password']);
    }
    public function getUsers(): array
    {
        $sql = "SELECT * FROM user";
        $stmt = $this->con->prepare($sql);
        $stmt->execute();
        $result = $stmt->fetchAll();
        $users = [];
        foreach ($result as $user) {
            $users[] = new User($user['id'], $user['username'], $user['password']);
        }
        return $users;
    }
    public function getHashedPasswordById(int $id): string
    {
        $sql = "SELECT password FROM user WHERE id=:id";
        $stmt = $this->con->prepare($sql);
        $stmt->bindValue(':id', $id);
        $stmt->execute();
        $result = $stmt->fetch();
        return $result['password'];
    }
    public function getUserId(string $username): int
    {
        $sql = "SELECT id FROM user WHERE username=:username";
        $stmt = $this->con->prepare($sql);
        $stmt->bindValue(':username', $username);
        $stmt->execute();
        $result = $stmt->fetch();
        return $result['id'];
    }
    public function getUserByUsernameAndPassword(string $username, string $password): User
    {
        $sql = "SELECT * FROM user WHERE username=:username AND password=:password";
        $stmt = $this->con->prepare($sql);
        $stmt->bindValue(':username', $username);
        $stmt->bindValue(':password', $password);
        $stmt->execute();
        $result = $stmt->fetch();
        return new User($result['id'], $result['username'], $result['password']);
    }
}
