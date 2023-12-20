<?php
namespace controller;

use config\Validation;
use model\IdSessionDoubleException;
use model\PseudoDejaPrisException;
use model\ValidationException;
use model\MdlInvite;

class _TO_DELETE__PseudoController{

    public function defaultAction(){
        global $twig, $config;
        global $basePath;
        $dVue = [];
        $dVueErreur = [];

        if(isset($_POST["pseudo"])){
            try{
                $mdlInvite = new MdlInvite();
                $pseudo = $_POST["pseudo"];
                $pseudo = Validation::valPseudo($pseudo, $dVueErreur);
                $role = $mdlInvite->insertInvite($pseudo, session_id());
            }catch(ValidationException $e){
                
            }catch(PseudoDejaPrisException $e){
                $dVueErreur[] = "Pseudo déjà pris";
            }catch(IdSessionDoubleException $e){
                try{
                    $role = $mdlInvite->setPseudo($e->getIdJoueur(), $pseudo);

                }catch(PseudoDejaPrisException $e){
                    $dVueErreur[] = "Pseudo déjà pris";
                }
            }

            if(isset($role)){
                $_SESSION['role'] = $role;
                header('Location: '.$basePath.'/jouer');
            }
        }
        echo $twig->render($config['templates']['pseudo'], ["dVue" => $dVue, "dVueErreur" => $dVueErreur]);
    }
}