<?php

namespace model;

class Admin
{
    private int $id;
    private string $email;
    private string $password;

    /**
     * @param int $id
     * @param string $password
     * @param string $email
     */
    public function __construct(int $id, string $email, string $password)
    {
        $this->id=$id;
        $this->password=$password;
        $this->email = $email;
    }

    /**
     * @return string
     */
    public function getEmail(): string
    {
        return $this->email;
    }

    /**
     * @return int
     */
    public function getId(): int
    {
        return $this->id;
    }   
    
    /**
    * @return string
    */
   public function getPassword(): string
   {
       return $this->password;
   }

}