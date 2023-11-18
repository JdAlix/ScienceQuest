<?php

namespace model;

class Invite extends Joueur{
    private string $idSession;

    public function  __construct(int $id, string $pseudo, string $idSession)
    {
        parent::__construct($id,$pseudo);
        $this->idSession = $idSession;
    }

    public function getIdSession(): string
    {
        return $this->idSession;
    }
}