<?php

namespace controller;

use config\Validation;
use Exception;
use model\ConfigurationJeu;
use model\Connection;
use model\LoginException;
use model\MdlAdmin;
use model\MdlDifficulte;
use model\MdlJeu;
use model\MdlScientifique;
use model\MdlUser;
use model\PseudoDejaPrisException;
use model\ValidationException;

class InviteController
{

    public function defaultAction(array $params) {
        global $twig, $dVue;

        echo $twig->render('accueil.html', ["dVue"=>$dVue]);
    }

    public function historique(array $params) {
        global $twig;
        global $dVueErreur;
        global $dVue;

        $ms = new MdlScientifique();

        if (!isset($params['id'])) {
            $page = 1;
        } else {
            $page = Validation::valPosInt($params['id']);
        }

        $pseudo = Validation::valPseudo($_SESSION['pseudo'],$dVueErreur);

        $dVue['listeScientifiques'] = $ms->getHistoriqueParPage($pseudo,$page);
        $dVue['pageMax'] = $ms->getMaxPagesHistorique($pseudo);
        $dVue['page'] = $page;

        if ($page - 1 <= 0) {
            $dVue['pagePrec'] = 1;
        } else {
            $dVue['pagePrec'] = $page - 1;
        }
        if ($page + 1 >= $dVue['pageMax']) {
            $dVue['pageSuiv'] = $dVue['pageMax'];
        } else {
            $dVue['pageSuiv'] = $page + 1;
        }

        echo $twig->render('historique.html',['dVue' => $dVue]);
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
                        new _TO_DELETE__ScienceQuizzController($role, $configurationJeu);
                        break;
                    case 3:
                        new _TO_DELETE__PenduController($role, $configurationJeu);
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