<?php
namespace controller;

use config\Validation;
use model\IdSessionDoubleException;
use model\PseudoDejaPrisException;
use model\ValidationException;
use model\MdlInvite;

class PseudoController{

    public function __construct(){
        global $twig, $config;
        $dVue = [];
        $dVueErreur = [];

        if(isset($_POST["pseudo"])){
            try{
                $mdlInvite = new MdlInvite();
                $pseudo = $_POST["pseudo"];
                $pseudo = Validation::valPseudo($pseudo, $dVueErreur);
                $role = $mdlInvite->insertInvite($pseudo, session_id());
            }catch(ValidationException){
                
            }catch(PseudoDejaPrisException){
                $dVueErreur[] = "Pseudo déjà pris";
            }catch(IdSessionDoubleException $e){
                $role = $mdlInvite->setPseudo($e->getIdJoueur(), $pseudo);
            }

            if(isset($role)){
                $_SESSION['role'] = $role;
                header('Location: jouer');
            }
        }
        echo $twig->render($config['templates']['pseudo'], ["dVue" => $dVue, "dVueErreur" => $dVueErreur]);
    }
}