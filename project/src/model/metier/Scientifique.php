<?php

namespace model;

use DateTime;

class Scientifique
{
    private int $id;
    private string $nom;
    private string $prenom;
    private string $photo;
    private DateTime $dateNaiss;
    private string $descriptif;
    private float $ratioTrouvee;
    private Thematique $thematique;
    private Difficulte $difficulte;
    private Sexe $sexe;

    /**
     * @param int $id
     * @param string $nom
     * @param string $prenom
     * @param string $photo
     * @param string $descriptif
     * @param Thematique $thematique
     * @param Difficulte $difficulte
     * @param Sexe $sexe
     */
    public function __construct(int $id, 
                                string $nom, 
                                string $prenom, 
                                string $photo, 
                                DateTime $dateNaiss, 
                                string $descriptif, 
                                float $ratioTrouvee,
                                Thematique $thematique, 
                                Difficulte $difficulte,
                                Sexe $sexe)
    {
        $this->id = $id;
        $this->nom = $nom;
        $this->prenom = $prenom;
        $this->photo = $photo;
        $this->ratioTrouvee = $ratioTrouvee;
        $this->descriptif = $descriptif;
        $this->dateNaiss = $dateNaiss;
        $this->thematique = $thematique;
        $this->difficulte = $difficulte;
        $this->sexe = $sexe;
    }

    /**
     * @return int
     */
    public function getId(): int
    {
        return $this->id;
    }

    /**
     * @return string
     */
    public function getNom(): string
    {
        return $this->nom;
    }

    /**
     * @return string
     */
    public function getPrenom(): string
    {
        return $this->prenom;
    }

    /**
     * @return string
     */
    public function getPhoto(): string
    {
        return $this->photo;
    }

    /**
     * @return string
     */
    public function getDescriptif(): string
    {
        return $this->descriptif;
    }

    /**
     * @return DateTime
     */
    public function getDateNaiss(): DateTime
    {
        return $this->dateNaiss;
    }

    /**
     * @return float
     */
    public function getRatioTrouvee(): float
    {
        return $this->ratioTrouvee;
    }

    /**
     * @return Thematique
     */
    public function getThematique(): Thematique
    {
        return $this->thematique;
    }

    /**
     * @return Difficulte
     */
    public function getDifficulte(): Difficulte
    {
        return $this->difficulte;
    }

    /**
     * @return Sexe
     */
    public function getSexe(): Sexe
    {
        return $this->sexe;
    }
}