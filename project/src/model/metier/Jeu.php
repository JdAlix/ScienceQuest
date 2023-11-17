<?php

namespace model;

class Jeu
{
    private int $id;
    private string $nom;
    private int $nbParties;

    /**
     * @param int $id
     * @param string $nom
     * @param string $nbParties
     */
    public function __construct(int $id, string $nom, int $nbParties)
    {
        $this->id=$id;
        $this->nom=$nom;
        $this->nbParties=$nbParties;
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
    public function getNom(): string{
        return $this->nom;
    }

        /**
     * @return int
     */
    public function getNbParties():int{
        return $this->nbParties;
    }

    /**
     * @return int
     */
    public function incrementNbParties(): int{
        $this->nbParties += 1;
        return $this->nbParties;
    }
}