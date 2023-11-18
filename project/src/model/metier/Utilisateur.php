<?php

namespace model;

class Utilisateur extends Joueur{
    private string $email;
    private string $motDePasse; #hash du mot de passe

    public function  __construct(int $id, string $pseudo, string $email, string $motDePasse)
    {
        parent::__construct($id,$pseudo);
        $this->email = $email;
        $this->motDePasse = $motDePasse;
    }

    public function getEmail(): string
    {
        return $this->email;
    }

    public function getMotDePasse(): string
    {
        return $this->motDePasse;
    }
}