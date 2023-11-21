<?php

namespace model;

class Difficulte
{
    private int $id;
    private string $libelle;

    /**
     * @param int $id
     * @param string $libelle
     */
    public function __construct(int $id, string $libelle)
    {
        $this->id=$id;
        $this->libelle=$libelle;
    }

    /**
     * @return int
     */
    public function getId(): int{
        return $this->id;
    }
    
    /**
     * @return string
     */
    public function getLibelle(): string{
        return $this->libelle;
    }

    public function __toString() {
        return $this->libelle;
    }
}