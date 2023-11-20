<?php

namespace model;


abstract class Joueur{
    private int $id;
    private string $pseudo;

    public function __construct(int $id, string $pseudo){
        $this->id=$id;
        $this->pseudo=$pseudo;
    }

    public function getId(): int
    {
        return $this->id;
    }

    public function getPseudo(): String
    {
        return $this->pseudo;
    }
}