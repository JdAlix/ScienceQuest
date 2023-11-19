<?php

namespace controller;

use model\ConfigurationJeu;
use model\Joueur;
use model\MdlScientifique;

class PenduController{
    public function __construct(Joueur $role, ConfigurationJeu $configJeu)
    {
        echo "pendu";
        $t = new MdlScientifique();
        var_dump($t->getRandom());
    }
}