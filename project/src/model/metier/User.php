<?php

namespace model;

class User
{
    private int $id;
    private string $username;
    private string $password;

    /**
     * @param int $id
     * @param string $username
     * @param string $password
     */

    public function __construct(int $id, string $username, string $password)
    {
        $this->id=$id;
        $this->username=$username;
        $this->password=$password;
    }
    public function getId(): int
    {
        return $this->id;
    }
    public function getUsername(): string
    {
        return $this->username;
    }
    public function setUsername(string $username): void
    {
        $this->username=$username;
    }
    public function setPassword(string $password): void
    {
        $this->password=$password;
    }
}