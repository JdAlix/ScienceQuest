<?php
namespace controller;

class FrontController
{
    public function __construct()
    {
        global $twig;

        session_start();

        // Tableau qui contient les messages d'erreur
        $dVueErreur = [];

        try {
            $action = $_REQUEST['action'] ?? null;

            switch($action) {
                case null:
                    echo $twig->render('accueil.html');
                    break;
                case 'join':
                    echo $twig->render('join.html');
                    break;
                case 'create':
                    $this->CreateParty();
                    break;
                case 'validationFormulaire':
                    $this->ValidationFormulaire($dVueErreur);
                    break;

                //mauvaise action
                default:
                    $dVueErreur[] = "Erreur d'appel php";
                    echo $twig->render('accueil.html', ['dVueErreur' => $dVueErreur]);
                    break;
            }
        } catch (\PDOException $e) {
            $dVueErreur[] = 'Erreur avec la base de données !';
            echo $twig->render('erreur.html', ['dVueErreur' => $dVueErreur]);
        } catch (\Exception $e2) {
            $dVueErreur[] = 'Erreur inattendue !';
            echo $twig->render('erreur.html', ['dVueErreur' => $dVueErreur]);
        }

        exit(0);
    }

    public function CreateParty() : void
    {
        global $twig;

        $dVueCreate = \model\GameGateway::getGames();
        echo $twig->render('create.html', ['dVueCreate' => $dVueCreate]);
    }

    public function ValidationFormulaire(array &$dVueErreur)
    {
        global $twig;

        $game = $_POST['game'] ?? '';
        $difficulty = $_POST['difficulty'] ?? '';
        \config\Validation::val_form($game, $difficulty, $dVueErreur);

        $dVue = [
            'info' => "Jeu '$game' créé avec la difficulté $difficulty"
        ];

        echo $twig->render('accueil.html', ['dVue' => $dVue, 'dVueErreur' => $dVueErreur]);
    }
}
