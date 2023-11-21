<?php

namespace controller;

use config\Validation;
use Exception;
use model\ConfigurationJeu;
use model\Connection;
use model\GameGateway;
use model\MdlDifficulte;
use model\MdlJeu;
use model\ValidationException;

class UserController {

    public function defaultAction(array $params) {
        global $twig;

        echo $twig->render('accueil.html');
    }

    public function joinParty(array $params) {
        global $twig;

        if(isset($_POST['codeInvitation'])){
            $codeInvitation = $_POST['codeInvitation'];
            Validation::valCodeInvitation($codeInvitation, $dVueErreur);
            echo $twig->render('join.html');
        }
    }

    /**
     * @throws Exception
     */
    public function jouer(array $params) {
        global $twig, $config;
        $dVue = [];
        $dVueErreur = [];

        if(isset($_SESSION["configuration"]) && isset($_SESSION['role'])){
            try{
                $role = $_SESSION['role'];
                $role = Validation::valRole($role, $dVueErreur);
                $configurationJeu = $_SESSION['configuration'];
                $configurationJeu = Validation::valConfigurationJeu($configurationJeu, $dVueErreur);
            }catch(ValidationException $e){
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
                }
            }
        }else{
            header("Location: .");
        }
    }

    public function createParty(array $params) : void
    {
        global $twig;
        global $dVueErreur;

        $listJeu = (new MdlJeu())->getAll();
        $listDifficulte = (new MdlDifficulte())->getAll();

        $dVueCreateJeu = [];
        foreach($listJeu as $jeu){
            $dVueCreateJeu[] = ['id' => $jeu->getId(), 'nom' => $jeu->getNom()];
        }

        $dVueCreateDifficulte = [];
        foreach($listDifficulte as $difficulte){
            $dVueCreateDifficulte[] = ['id' => $difficulte->getId(), 'libelle' => $difficulte->getLibelle()];
        }

        echo $twig->render('create.html', ["dVueErreur" => $dVueErreur, 'dVueCreate' => ["jeux" => $dVueCreateJeu, "difficultes" => $dVueCreateDifficulte]]);
    }

    public function ValidationFormulaire(array $params)
    {
        global $twig;
        global $dVue;
        global $dVueErreur;
        global $basePath;

        $id_jeu = $_POST['jeu'] ?? '';
        $id_difficulte = $_POST['difficulte'] ?? '';
        try{
            Validation::val_form($id_jeu, $id_difficulte, $dVueErreur);
        }catch (ValidationException|Exception $ex){
            $this->CreateParty($dVueErreur);
        }

        if(count($dVueErreur) == 0){
            $jeu = (new MdlJeu())->getFromId($id_jeu);
            $difficulte = (new MdlDifficulte())->getFromId($id_difficulte);
            $_SESSION['configuration'] = new ConfigurationJeu($jeu, $difficulte);

            header("Location: ".$basePath."/pseudo");
            #echo $twig->render('accueil.html', ['dVue' => $dVue, 'dVueErreur' => $dVueErreur]);
        }else{
            $this->CreateParty($dVueErreur);
        }
    }
}