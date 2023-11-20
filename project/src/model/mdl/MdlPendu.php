<?php

namespace model;

class MdlPendu{
    private Scientifique $scientifiqueATrouver;
    private string $aTrouver;
    private int $nbFail;
    private int $nbTours;
    private array $lettreExceptees;
    private array $lettreUtilisees;

    private static $nbFailsMax = 8;

    public function __construct(Scientifique $scientifiqueATrouver){
        $this->scientifiqueATrouver = $scientifiqueATrouver;
        $this->aTrouver = strtr($scientifiqueATrouver->getPrenom()." ".$scientifiqueATrouver->getNom(), 'áàâäãåçéèêëíìîïñóòôöõúùûüýÿ', 'aaaaaaceeeeiiiinooooouuuuyy'); #suppression des accents
        $this->nbFail = 0;
        $this->nbTours = 0;
        $this->lettreExceptees = [' ', '-'];
        $this->lettreUtilisees = [];
    }

    public function jouerLettre(string $lettre): string{
        $lettre = strtolower($lettre);
        if(strlen($lettre) != 1){ throw new ValidationException("Veuillez fournir une seule lettre");}
        if(!ctype_alpha($lettre)){ throw new ValidationException("Veuillez fournir une caractère alphabétique");}
        if(in_array($lettre, $this->lettreUtilisees)){ throw new ValidationException("Vous avez déjà joué cette lettre");}
        $this->nbTours += 1;

        if(!str_contains($this->aTrouver, $lettre)){
            $this->nbFail += 1;
        }
        $this->lettreUtilisees[] = $lettre;
        return $this->getDecouvert();
    }

    public function getScientifique(): Scientifique
    {
        return $this->scientifiqueATrouver;
    }

    public function getLettreUtilisees(): array{
        return $this->lettreUtilisees;
    }

    public function getATrouver(): string
    {
        return $this->aTrouver;
    }

    public function getDecouvert(): string
    {
        $decouvert = '';
        foreach(str_split($this->aTrouver) as $lettre){
            if(in_array(strtolower($lettre), $this->lettreUtilisees) || in_array(strtolower($lettre), $this->lettreExceptees)){
                $decouvert.=$lettre;
            }else{
                $decouvert.="_";
            }
        }
        return $decouvert;
    }

    public function getNbTours():int{
        return $this->nbTours;
    }

    public function getNbFails():int{
        return $this->nbFail;
    }

    public function getEssaisRestant():int{
        return $this::$nbFailsMax - $this->nbFail;
    }

    public function aGagne(): bool{
        return $this->getDecouvert() == $this->getATrouver();
    }

    public function aPerdu(): bool{
        return $this->getEssaisRestant() <= 0;
    }
    
}