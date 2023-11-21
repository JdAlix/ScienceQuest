<?php

namespace controller;

use Exception;
use model\ConfigurationJeu;
use model\Connection;
use model\Joueur;
use model\MdlScienceQuizz;
use model\MdlScientifique;
use config\Validation;
use model\ScientifiqueGateway;
use model\ValidationException;
use Twig\Error\LoaderError;
use Twig\Error\RuntimeError;
use Twig\Error\SyntaxError;

class ScienceQuizzController
{
    private array $dVue;
    private array $dVueErreur;
    private MdlScienceQuizz $scienceQuizz;
    private Connection $con;

    /**
     * @throws RuntimeError
     * @throws SyntaxError
     * @throws LoaderError
     * @throws Exception
     */
    public function __construct(Joueur $role, ConfigurationJeu $configJeu)
    {
        $this->dVue = [];
        $this->dVueErreur = [];
        if (isset($_SESSION['scienceQuizz']) && Validation::valMdlScienceQuizz($_SESSION['scienceQuizz'], $this->dVueErreur)) {
            $this->scienceQuizz = $_SESSION['scienceQuizz'];
        } else {
            $this->reInit();
        }

        if ($this->scienceQuizz->partieTerminee()) {
            $this->vueRecap();
        }
        else {
            $this->vueJeu();
        }
    }

    /**
     * @throws Exception
     */
    private function reInit()
    {
        $mdlScientifique = new MdlScientifique();
        $scientifique = $mdlScientifique->getRandom();
        $idScientifique = $scientifique->getId();
        $questions = $mdlScientifique->getQuestions($idScientifique);
        $this->scienceQuizz = new MdlScienceQuizz($idScientifique,$questions);
        $_SESSION['scienceQuizz'] = $this->scienceQuizz;
    }

    /**
     * @throws SyntaxError
     * @throws RuntimeError
     * @throws LoaderError
     */
    private function vueJeu()
    {
        global $twig, $config;

        $dStatJeu['numQuestion'] = $this->scienceQuizz->getNumQuestion();

        $questions = $this->scienceQuizz->getQuestions();

        if ($questions) {
            $dStatJeu['question'] = $this->scienceQuizz->getRandomQuestion($questions);
        } else {
            // Gérer le cas où aucune question n'est disponible
            $this->dVueErreur[] = "Aucune question disponible.";
            echo $twig->render('erreur.html', ['dVueErreur' => $this->dVueErreur]);
            return;
        }

        $this->dVue['statJeu'] = $dStatJeu;

        echo $twig->render($config['templates']['scienceQuizz'], ['dVue' => $this->dVue, 'dVueErreur' => $this->dVueErreur]);
        ?>
        <script>
            setTimeout(function() {
                window.location.href = "/scienceQuizzReponse.html";
            }, 30000);
        </script>
        <?php
    }

    /**
     * @throws RuntimeError
     * @throws SyntaxError
     * @throws LoaderError
     */
    private function vueReponse()
    {
        global $twig, $config;

        $dScientifique['nom'] = $this->scienceQuizz->getNom();
        $dScientifique['prenom'] = $this->scienceQuizz->getPrenom();
        $dScientifique['dateNaissance'] = $this->scienceQuizz->getDateNaissance();


        $this->dVue['scientifique'] = $dScientifique;

        echo $twig->render($config['templates']['scienceQuizzReponse'], ["dVue" => $this->dVue]);
    }

    /**
     * @throws RuntimeError
     * @throws SyntaxError
     * @throws LoaderError
     */
    private function vueRecap()
    {
        global $twig, $config;

        $dStatJoueur ['bonneReponse'] = $this->scienceQuizz->getBonneReponse();
        $dStatJoueur ['nbPoints'] = $this->scienceQuizz->getNbPoints();

        $this->dVue['statJoueur'] = $dStatJoueur;
        unset($_SESSION['scienceQuizz']);

        echo $twig->render($config['templates']['scienceQuizzRecap'], ["dVue" => $this->dVue]);
    }
}