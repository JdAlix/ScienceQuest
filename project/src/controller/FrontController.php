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

        global $twig, $router;
        global $basePath;

        //altorouter
        $router = new AltoRouter();
        $router->setBasePath($basePath);
        
        // Correspond à action = null et permet d'éviter une erreur sur la page /index.php
        $router->map('GET|POST','/index.php','UserController');

        $router->map('GET|POST','/pseudo/[a:action]?','PseudoController');
        $router->map('GET|POST','/admin/[a:action]','AdminController');
        $router->map('GET|POST','/[a:action]?','UserController');
        $router->map('GET|POST','/login','login');

        session_start();

        if(isset($_SESSION['pseudo']))
            $dVue['pseudo'] = $_SESSION['pseudo'];

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
                    $action = $match['params']['action'];
                    //if (!MdlAdmin::isAdmin()) {
                    //    $action = 'login';
                    //}
                    new AdminController($action);
                    break;

                case 'PseudoController':
                    $this->callController('PseudoController',$match);
                    break;
                //mauvaise action
                default:
                    $dVueErreur[] = "Erreur d'appel php";
                    echo $twig->render('accueil.html', ['dVueErreur' => $dVueErreur]);
                    break;
            }
        } catch (PDOException $e) {
            $dVueErreur[] = 'Erreur avec la base de données !';
            $dVueErreur[] = $e->getMessage();
            echo $twig->render('erreur.html', ['dVueErreur' => $dVueErreur]);
        } catch (LoginException $e) {
            echo $twig->render('erreur.html', ['dVueErreur' => $dVueErreur]);
            echo $twig->render('login.html');
        } catch (Exception $e2) {
            $dVueErreur[] = 'Erreur inattendue !'.$e2->getMessage();
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
