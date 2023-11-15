<?php
namespace controller;

use config\Validation;
use http\Params;
use model\Connection;
use model\ModelAdmin;
use model\UserGateway;

class FrontController
{
    public function __construct()
    {
        global $twig, $router;
        global $login;
        global $mdp;
        global $base;

        $con = new Connection($base, $login, $mdp);

        //altorouter
        $router = new \AltoRouter();
        $router->setBasePath('~rebeuret/ScienceQuest/Project/src/');

        $router->map('GET|POST','/[a:action]?','UserController');
        $router->map('GET|POST','/admin/[a:action]','AdminController');
        // $router->map('GET|POST', '/', 'null');
        // $router->map('GET|POST', '/join', 'join');
        // $router->map('GET|POST', '/create', 'create');
        // $router->map('GET|POST', '/login', 'login');
        // $router->map('GET|POST', '/admin/[a:action]?', 'admin');
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
                case 'UserController':
                    $this->callController('UserController',$match);
                    break;

                case 'AdminController':
                    $action = $match['params']['action'];
                    if (!ModelAdmin::isAdmin()) {
                        $action = 'login';
                    }
                    $this->callController('AdminController',$action);
                    break;

                case 'validationFormulaire':
                    $this->ValidationFormulaire($dVueErreur, $dVue);
                    break;

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

    private function callController(string $cont, array $match) {
        global $twig;

        $controller = '\\controller\\'.$cont;
        $controller = new $controller;
        $action = $match['params']['action'] ?? 'accueil';

        if (is_callable(array($controller,$action))) {
            call_user_func_array(array($controller,$action),array($match['params']));
        } else {
            echo $twig->render('erreur.html', ['dVueErreur' => array('Page inconnue')]);
        }
    }

    public function ValidationFormulaire(array &$dVueErreur, array &$dVue)
    {
        global $twig;

        $game = $_POST['game'] ?? '';
        $difficulty = $_POST['difficulty'] ?? '';
        \config\Validation::val_form($game, $difficulty, $dVueErreur);

        $dVue['info'] = "Jeu '$game' créé avec la difficulté $difficulty";

        echo $twig->render('accueil.html', ['dVue' => $dVue, 'dVueErreur' => $dVueErreur]);
    }
}
