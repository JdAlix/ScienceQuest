<?php
namespace controller;

use config\Validation;
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
                $pseudo = $role->getPseudo();
                $nomJeu = $configurationJeu->getJeu()->getNom();
                $libelleDifficulte = $configurationJeu->getDifficulte()->getLibelle();

                $dVue["nomJeu"] = $nomJeu;
                $dVue["pseudo"] = $pseudo;
                $dVue["libelleDifficulte"] = $libelleDifficulte;

                echo $twig->render($config["templates"]["jouer"], ["dVue"=>$dVue]);
            }
        }else{
            header("Location: .");
        }
    }
}