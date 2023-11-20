<?php
namespace controller;

use config\Validation;
use http\Params;
use model\Connection;
use model\LoginException;
use model\MdlAdmin;
use model\MdlUser;

class FrontController
{
    private Connection $con;
    public function __construct()
    {
        global $twig, $router;
        global $basePath;

        //altorouter
        $router = new \AltoRouter();
        $router->setBasePath($basePath);

        $router->map('GET|POST','/[a:action]?','UserController');
        $router->map('GET|POST','/admin/[a:action]','AdminController');
        $router->map('GET|POST', '/validationFormulaire', 'validationFormulaire');
        $router->map('GET|POST', '/logout', 'disconnect');


        // Tableau qui contient les messages d'erreur
        $dVueErreur = [];
        $dVue = [];
        $dVue['basePath'] = $basePath;

        session_start();

        if(isset($_SESSION['pseudo']))
            $dVue['pseudo'] = $_SESSION['pseudo'];

        try {
            $match = $router->match();

            if (!$match) {
                throw new \Exception('Wrong call');
            }

            switch($match['target']) {

                case 'UserController':
                    $this->callController('UserController',$match);
                    break;

                case 'AdminController':
                    $action = $match['params']['action'];
                    if (!MdlAdmin::isAdmin()) {
                        $action = 'login';
                    }
                    $this->callController('AdminController',$action);

                case 'PseudoController':
                    $this->callController('PseudoController',$match);
                    break;

                case 'validationFormulaire':
                    $this->ValidationFormulaire($dVueErreur, $dVue);
                    break;

                //mauvaise action
                default:
                    $dVueErreur[] = "Erreur d'appel php";
                    echo $twig->render('accueil.html', ['dVueErreur' => $dVueErreur]);
                    break;
            }
        } catch (\PDOException $e) {
            $dVueErreur[] = 'Erreur avec la base de données !';
            $dVueErreur[] = $e->getMessage();
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

        $id_jeu = $_POST['jeu'] ?? '';
        $id_difficulte = $_POST['difficulte'] ?? '';
        try{
            \config\Validation::val_form($id_jeu, $id_difficulte, $dVueErreur);
        }catch (\model\ValidationException $ex){
            $this->CreateParty($dVueErreur);
        }catch (\Exception $ex){
            $this->CreateParty($dVueErreur);
        }

        if(count($dVueErreur) == 0){
            $jeu = (new \model\MdlJeu())->getFromId($id_jeu);
            $difficulte = (new \model\MdlDifficulte())->getFromId($id_difficulte);
            $_SESSION['configuration'] = new \model\ConfigurationJeu($jeu, $difficulte);

            header("Location: /pseudo");
            #echo $twig->render('accueil.html', ['dVue' => $dVue, 'dVueErreur' => $dVueErreur]);    
        }else{
            $this->CreateParty($dVueErreur);
        }
       }
}
