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
use model\MdlScientifique;
use model\PseudoDejaPrisException;
use model\ValidationException;
use model\MdlUser;
use model\MdlAdmin;
use model\LoginException;
use model\Utilisateur;

class UserController extends InviteController {


    public function listerLesScientifiquesDecouverts() {

    }

    public function afficherDetailScientifique() {

    }

    public function login() {
        global $twig;
        if ($_SERVER['REQUEST_METHOD'] === 'POST') {
            Validation::valUserLogin($_REQUEST['login'], $dVueErreur);
            $ug = new MdlUser();
            if ($ug->login($_REQUEST['login'], $_REQUEST['password'])) {
                $_SESSION['pseudo'] = $_REQUEST['login'];
                $_SESSION['idUser'] = true;
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
}