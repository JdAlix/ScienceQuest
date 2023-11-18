<?php

namespace model;

class IdSessionDoubleException extends \RuntimeException
{
    private int $idJoueur;
    private string $idSession;

    public function __construct($idJoueur, $idSession)
    {
        parent::__construct();
        $this->idJoueur=$idJoueur;
        $this->idSession=$idSession;
    }

    public function getIdJoueur():int{
        return $this->idJoueur;
    }

    public function getIdSession(): string{
        return $this->idSession;
    }
}