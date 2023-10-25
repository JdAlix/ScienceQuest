<?php

namespace model;

class Admin extends User
{
    private string $email;

    /**
     * @param int $id
     * @param string $username
     * @param string $password
     * @param string $email
     */
    public function __construct(int $id, string $username, string $password, string $email)
    {
        parent::__construct($id, $username, $password);
        $this->email = $email;
    }

    /**
     * @return string
     */
    public function getEmail(): string
    {
        return parent::getUsername();
    }

    /**
     * @param string $email
     */
    public function setEmail(string $email): void
    {
        parent::setUsername($email);
    }
}