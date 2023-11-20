<?php

namespace model;

class ConfigurationJeu{
    private Difficulte $difficulte;
    private Jeu $jeu;
    
    public function __construct(Jeu $jeu, Difficulte $difficulte){
        $this->jeu = $jeu;
        $this->difficulte = $difficulte;
    }

    public function getDifficulte(): Difficulte{
        return $this->difficulte;
    }

    public function getJeu(): Jeu{
        return $this->jeu;
    }
}