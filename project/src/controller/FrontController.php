<?php
namespace controller;

use config\Validation;
use model\Connection;
use model\LoginException;
use model\UserGateway;

class FrontController
{
    private Connection $con;
    public function __construct()
    {
        global $twig, $router, $config;

        $this->con = new Connection($config["db"]["dsn"], $config["db"]["login"], $config["db"]["mdp"]);

        $router->map('GET|POST', '/', 'null');
        $router->map('GET|POST', '/join', 'join');
        $router->map('GET|POST', '/create', 'create');
        $router->map('GET|POST', '/login', 'login');
        $router->map('GET|POST', '/admin/[a:action]?', 'admin');
        $router->map('GET|POST', '/validationFormulaire', 'validationFormulaire');
        $router->map('GET|POST', '/logout', 'disconnect');


        // Tableau qui contient les messages d'erreur
        $dVueErreur = [];
        $dVue = [];

        session_start();

        if(isset($_SESSION['pseudo']))
            $dVue['pseudo'] = $_SESSION['pseudo'];

        try {
            $match = $router->match();
            if (!$match) {
                throw new \Exception('Wrong call');
            }
            $action=$match['params']['action'] ?? "";

            

            switch($match['target']) {
                case 'null':
                    echo $twig->render('accueil.html', ['dVue' => $dVue]);
                    break;
                case 'join':
                    if(isset($_POST['codeInvitation'])){
                        $codeInvitation = $_POST['codeInvitation'];
                        Validation::valCodeInvitation($codeInvitation, $dVueErreur);
                        echo $twig->render('join.html');
                    }
                    break;
                case 'create':
                    $this->CreateParty();
                    break;
                case 'validationFormulaire':
                    $this->ValidationFormulaire($dVueErreur, $dVue);
                    break;
                case 'admin':
                    new AdminController($action);
                    break;
                case 'login':
                    if(empty($_SESSION) && !isset($_REQUEST['login']))
                        echo $twig->render('login.html');
                    elseif(isset($_REQUEST['login'])) {
                        Validation::valUserLogin($_REQUEST['login'], $dVueErreur);
                        $ug = new UserGateway($this->con);
                        if($ug->login($_REQUEST['login'], $_REQUEST['password'])) {
                            $_SESSION['pseudo'] = $_REQUEST['login'];
                            header("Location: .");
                        } else {
                            $dVueErreur[] = "Connexion échouée";
                            throw new LoginException("Connexion err");
                        }
                    } else
                        header("Location: .");
                    break;
                case 'disconnect':
                    session_unset();
                    session_destroy();
                    $_SESSION = array();
                    header("Location: .");
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
        } catch (LoginException $e) {
            echo $twig->render('erreur.html', ['dVueErreur' => $dVueErreur]);
            echo $twig->render('login.html');
        } catch (\Exception $e2) {
            $dVueErreur[] = 'Erreur inattendue !'.$e2->getMessage();
            echo $twig->render('erreur.html', ['dVueErreur' => $dVueErreur]);
        }

        exit(0);
    }

    public function CreateParty() : void
    {
        global $twig;
        $listJeu = (new \model\JeuGateway($this->con))->getAll();
        $listDifficulte = (new \model\DifficulteGateway($this->con))->getAll();
        
        $dVueCreateJeu = [];
        foreach($listJeu as $jeu){
            $dVueCreateJeu[] = ['id' => $jeu->getId(), 'nom' => $jeu->getNom()];
        }

        $dVueCreateDifficulte = [];
        foreach($listDifficulte as $difficulte){
            $dVueCreateDifficulte[] = ['id' => $difficulte->getId(), 'libelle' => $difficulte->getLibelle()];
        }

        echo $twig->render('create.html', ['dVueCreate' => ["jeux" => $dVueCreateJeu, "difficultes" => $dVueCreateDifficulte]]);
    }

    public function ValidationFormulaire(array &$dVueErreur, array &$dVue)
    {
        global $twig;

        $id_jeu = $_POST['jeu'] ?? '';
        $id_difficulte = $_POST['difficulte'] ?? '';
        \config\Validation::val_form($id_jeu, $id_difficulte, $dVueErreur);

        $dVue['nomJeu'] = (new \model\JeuGateway($this->con))->getFromId($id_jeu)->getNom();
        $dVue['libelleDifficulte'] = (new \model\DifficulteGateway($this->con))->getFromId($id_difficulte)->getLibelle();

        echo $twig->render('accueil.html', ['dVue' => $dVue, 'dVueErreur' => $dVueErreur]);
    }
}
