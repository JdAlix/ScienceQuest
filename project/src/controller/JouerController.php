<?php
namespace controller;

use config\Validation;
use Exception;
use model\ValidationException;

class JouerController{

    /**
     * @throws Exception
     */
    public function __construct(){
        global $twig, $config;
        global $dVue;
        global $dVueErreur;
        global $basePath;

        if(isset($_SESSION["configuration"]) && isset($_SESSION['role'])){
            try{
                $role = $_SESSION['role'];
                $role = Validation::valRole($role, $dVueErreur);
                $configurationJeu = $_SESSION['configuration'];
                $configurationJeu = Validation::valConfigurationJeu($configurationJeu, $dVueErreur);
            }catch(ValidationException $e){
                header('Location: '.$basePath);
            }
            
            if(count($dVueErreur) == 0){
                $idJeu = $configurationJeu->getJeu()->getId();
                switch($idJeu){
                    case 2:
                        new ScienceQuizzController($role, $configurationJeu);
                        break;
                    case 3:
                        new PenduController($role, $configurationJeu);
                        break;
                    default:
                        throw new Exception("Jeu non défini !");
                }
            }
        }else{
            header("Location: ".$basePath);
        }
    }
}