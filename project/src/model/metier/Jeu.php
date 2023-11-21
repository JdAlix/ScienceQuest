<?php

namespace model;

class Jeu
{
    private int $id;
    private string $nom;
    private int $nbrParties;

    /**
     * @param int $id
     * @param string $nom
     * @param int $nbrParties
     */
    public function __construct(int $id, string $nom, int $nbrParties)
    {
        $this->id=$id;
        $this->nom=$nom;
        $this->nbrParties=$nbrParties;
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
        return $this->nbrParties;
    }

    /**
     * @return int
     */
    public function incrementNbParties(): int{
        $this->nbrParties += 1;
        return $this->nbrParties;
    }
}