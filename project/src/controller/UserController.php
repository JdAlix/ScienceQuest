<?php

namespace controller;

use config\Validation;
use Exception;
use model\ConfigurationJeu;
use model\Connection;
use model\GameGateway;
use model\Joueur;
use model\MdlDifficulte;
use model\MdlJeu;
use model\PseudoDejaPrisException;
use model\ValidationException;
use model\MdlUser;
use model\MdlAdmin;
use model\LoginException;
use model\Utilisateur;

class UserController {

    public function defaultAction(array $params) {
        global $twig, $dVue;

        echo $twig->render('accueil.html', ["dVue"=>$dVue]);
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
            header("Location: .");
        }
    }

    public function login() {
        global $twig;
        if ($_SERVER['REQUEST_METHOD'] === 'POST') {
            Validation::valUserLogin($_REQUEST['login'], $dVueErreur);
            $ug = new MdlUser();
            if ($ug->login($_REQUEST['login'], $_REQUEST['password'])) {
                $_SESSION['pseudo'] = $_REQUEST['login'];
                $_SESSION['isLogged'] = true;
                $_SESSION['role'] = $ug->getFromEmail($_REQUEST['login']);
                header("Location: .");
            } else {
                //voir si c'est un admin
                $ug = new MdlAdmin();
                if ($ug->login($_REQUEST['login'], $_REQUEST['password'])) {
                    $_SESSION['pseudo'] = $_REQUEST['login'];
                    $_SESSION['isAdmin'] = true;
                    $_SESSION['isLogged'] = true;
                    unset($_SESSION['role']);
                    header("Location: .");
                } else {
                    $dVueErreur[] = "Connexion échouée";
                    throw new LoginException("Connexion err");
                }
            }
        } else {
            echo $twig->render('login.html');
        }
    }

    public function register() {
        global $twig, $dVueErreur;
        if ($_SERVER['REQUEST_METHOD'] === 'POST') {
            if($_REQUEST['password']!=$_REQUEST['cpassword']){
                $dVueErreur[]="Mots de passe différents.";
                echo $twig->render('erreur.html',["dVueErreur" => $dVueErreur]);
                return;
            }
            $ug = new MdlUser();
            try{
            if($ug->register($_REQUEST['login'], $_REQUEST['password'])){
                header('Location: login');
            } else {
                $dVueErreur[]="Erreur de création de compte. Le compte doit déjà exister.";
                echo $twig->render('erreur.html',["dVueErreur" => $dVueErreur]);
            }
        } catch (PseudoDejaPrisException $ex){
            $dVueErreur[]="Erreur de création de compte. Le compte existe déjà.";
                echo $twig->render('erreur.html',["dVueErreur" => $dVueErreur]);
        }catch(Exception $ex){
            $dVueErreur[]="Erreur de création de compte.";
            echo $twig->render('erreur.html',["dVueErreur" => $dVueErreur]);
        }

        } else {
            echo $twig->render('register.html');
        }
    }

    public function logout(){
        session_destroy();
        $_SESSION=[];
        header("Location: .");
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

            if(isset($_SESSION['role'])){
                header('Location: '.$basePath.'/jouer');
            }else{
                header("Location: ".$basePath."/pseudo");
            }
            #echo $twig->render('accueil.html', ['dVue' => $dVue, 'dVueErreur' => $dVueErreur]);
        }else{
            $this->CreateParty($dVueErreur);
        }
    }
}