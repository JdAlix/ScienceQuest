<?php

namespace model;



use Exception;

class MdlScienceQuizz
{
    private int $bonneReponse;
    private string $nbPoints;
    private int $numQuestion;
    private array $reponses;
    private array $questions;
    private array $questionsPass;
    private bool $partieTerminee;

    private int $scientifique;

    public function __construct(int $scientifique, array $questions)
    {
        $this->bonneReponse = 0;
        $this->nbPoints = 0;
        $this->numQuestion = 0;
        $this->reponses = [];
        $this->scientifique = $scientifique;
        $this->questions = $questions;
        $this->questionsPass = [];
        $this->partieTerminee = false;
    }

    /**
     * @return array
     */
    public function getQuestions(): array
    {
        var_dump($this->questions);
        return $this->questions;
    }

    public function getScientifique(): int
    {
        return $this->scientifique;
    }

    /**
     * @return int
     */
    public function getBonneReponse(): int
    {
        return $this->bonneReponse;
    }

    /**
     * @return string
     */
    public function getNbPoints(): string
    {
        return $this->nbPoints;
    }

    /**
     * @return int
     */
    public function getNumQuestion(): int
    {
        return $this->numQuestion;
    }

    /**
     * @return array
     */
    public function getReponses(): array
    {
        return $this->reponses;
    }

    /**
     * @param int $bonneReponse
     */
    public function setBonneReponse(int $bonneReponse): void
    {
        $this->bonneReponse = $bonneReponse;
    }

    // Sélectionne une question aléatoire

    /**
     * @throws Exception
     */
    public function getRandomQuestion(array $questions): string
    {
        $randomNum=random_int(0, count($questions)-1);
        $question=$questions[$randomNum];
        $this->questionsPass[]=$question;
        if (count($this->questionsPass)==count($questions))
        {
            $this->partieTerminee=true;
        }
        return $question;
    }

    public function partieTerminee(): bool
    {
        return $this->partieTerminee;
    }

}