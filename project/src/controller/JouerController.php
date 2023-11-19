<?php
namespace controller;

use config\Validation;
use Exception;
use model\ValidationException;

class JouerController{

    public function __construct(){
        global $twig, $config;
        $dVue = [];
        $dVueErreur = [];

        if(isset($_SESSION["configuration"]) && isset($_SESSION['role'])){
            try{
                $role = $_SESSION['role'];
                $role = Validation::valRole($role, $dVueErreur);
                $configurationJeu = $_SESSION['configuration'];
                $configurationJeu = Validation::valConfigurationJeu($configurationJeu, $dVueErreur);
            }catch(ValidationException){
                header('Location: .');
            }
            
            if(count($dVueErreur) == 0){
                $idJeu = $configurationJeu->getJeu()->getId();
                switch($idJeu){
                    case 3:
                        new PenduController($role, $configurationJeu);
                        break;
                    default:
                        throw new Exception("Jeu non défini !");
                        break;
                }
            }
        }else{
            header("Location: .");
        }
    }
}