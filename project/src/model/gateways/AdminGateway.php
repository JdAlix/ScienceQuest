<?php

namespace model;

class AdminGateway 
{
    private \PDO $con;

    public function __construct(\PDO $con)
    {
        $this->con=$con;
    }

    public function login(string $username, string $password): bool
    {
        $sql = "SELECT * FROM admin WHERE username=:username";
        $stmt = $this->con->prepare($sql);
        $stmt->bindValue(':username', $username);
        $stmt->execute();
        $result = $stmt->fetch();
        if ($result && password_verify($password, $result['password'])) {
            return true;
        }
        return false;
    }
    public function getHashedPassword(string $username): string
    {
        $sql = "SELECT password FROM user WHERE username=:username";
        $stmt = $this->con->prepare($sql);
        $stmt->bindValue(':username', $username);
        $stmt->execute();
        $result = $stmt->fetch();
        return $result['password'];
    }
}