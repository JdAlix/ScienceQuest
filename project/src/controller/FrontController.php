<?php
namespace controller;

use AltoRouter;
use config\Validation;
use Exception;
use http\Params;
use model\ConfigurationJeu;
use model\Connection;
use model\LoginException;
use model\MdlAdmin;
use model\MdlDifficulte;
use model\MdlJeu;
use model\MdlUser;
use model\ValidationException;
use PDOException;

class FrontController
{
    private Connection $con;

    /**
     * @throws Exception
     */
    public function __construct()
    {

        global $twig, $router,  $dVue;
        global $basePath;
        global $dVueErreur;

        //altorouter
        $router = new AltoRouter();
        $router->setBasePath($basePath);
        
        // Correspond à action = null et permet d'éviter une erreur sur la page /index.php
        $router->map('GET|POST','/index.php','UserController');

        $router->map('GET|POST','/pseudo/[a:action]?','_TO_DELETE__PseudoController');
        $router->map('GET|POST','/admin/[a:action]?/[i:id]?','AdminController');
        $router->map('GET|POST','/[a:action]?/[i:id]?','UserController');

        try {
            session_start();
        } catch (Exception $e) {
            die('Session start failed: ' . $e->getMessage());
        }

        $dVue['idAdmin'] = false;
        $dVue['idUser'] = false;

        if(isset($_SESSION['pseudo'])) {
            $dVue['pseudo'] = $_SESSION['pseudo'];
            if (isset($_SESSION['isUser']) && $_SESSION['isUser']) {
                $dVue['isUser'] = true;
            } elseif (isset($_SESSION['idAdmin']) && $_SESSION['idAdmin']) {
                $dVue['isAdmin'] = true;
            }
        }

        try {
            $match = $router->match();

            if (!$match) {
                throw new Exception('Wrong call');
            }

            switch($match['target']) {

                case 'UserController':
                    $this->callController('UserController',$match);
                    break;

                case 'AdminController':
                    $action = $match['params']['action'] ?? '';
                    if (!MdlAdmin::isAdmin()) {
                        $match['params']['action'] = 'notLogged';
                    }
                    $this->callController('AdminController',$match);
                    break;

                case '_TO_DELETE__PseudoController':
                    $this->callController('_TO_DELETE__PseudoController',$match);
                    break;
                //mauvaise action
                default:
                    $dVueErreur[] = "Erreur d'appel php";
                    echo $twig->render('accueil.html', ['dVueErreur' => $dVueErreur]);
                    break;
            }
        } catch (LoginException $e) {
            echo $twig->render('erreur.html', ['dVueErreur' => $dVueErreur]);
            echo $twig->render('login.html');
        } catch (Exception $e2) {
            $dVueErreur[] = 'Erreur inattendue !';
            $dVueErreur[] = $e2->getMessage();
            echo $twig->render('erreur.html', ['dVueErreur' => $dVueErreur]);
        }

        exit(0);
    }

    private function callController(string $cont, array $match) {
        global $twig;

        $controller = '\\controller\\'.$cont;
        $controller = new $controller;
        $action = $match['params']['action'] ?? 'defaultAction';

        if (is_callable(array($controller,$action))) {
            call_user_func_array(array($controller,$action),array($match['params']));
        } else {
            echo $twig->render('erreur.html', ['dVueErreur' => array('Page inconnue')]);
        }
    }
}
