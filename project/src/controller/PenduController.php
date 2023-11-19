<?php

namespace controller;

use model\ConfigurationJeu;
use model\Joueur;
use model\MdlPendu;
use model\MdlScientifique;
use config\Validation;
use model\ValidationException;

class PenduController{
    private array $dVue;
    private array $dVueErreur;
    private MdlPendu $pendu;
    public function __construct(Joueur $role, ConfigurationJeu $configJeu)
    {
        $this->dVue = [];
        $this->dVueErreur = [];
        if(isset($_SESSION['pendu']) && Validation::valMdlPendu($_SESSION['pendu'], $this->dVueErreur)){
            $this->pendu = $_SESSION['pendu'];
        }else{
            $this->reinit();
        }

        if(isset($_POST['lettre'])){
            try{
                $this->pendu->jouerLettre($_POST['lettre']);
            }catch (ValidationException $e){
                $this->dVueErreur[] = $e->getMessage();
            }
        }
        
        if($this->pendu->aGagne()){
            $this->renderAgagne();
        }elseif($this->pendu->aPerdu()){
            $this->renderAPerdu();
        }else{
            $this->renderJeu();
        }
    }

    private function reinit(){
        $mdlScientifique = new MdlScientifique();
        $scientifique = $mdlScientifique->getRandom();
        $this->pendu = new MdlPendu($scientifique);
        $_SESSION['pendu'] = $this->pendu;
    }

    private function renderJeu(){
        global $twig, $config;
        $this->dVue['decouvert'] = $this->pendu->getDecouvert();
        $this->dVue['essaisRestant'] = $this->pendu->getEssaisRestant();
        $lettreUtilisees = $this->pendu->getLettreUtilisees();
        sort($lettreUtilisees);
        $this->dVue['lettresUtilisees'] = $lettreUtilisees;
        echo $twig->render($config['templates']['pendu'], ['dVue' => $this->dVue, 'dVueErreur'=>$this->dVueErreur]);
    }

    private function renderAgagne(){
        $this->dVue['messageScore'] = "Vous avez gagné !";
        $this->renderScore();
    }

    private function renderAPerdu(){
        $this->dVue['messageScore'] = "Vous avez perdu !";
        $this->renderScore();
    }

    private function renderScore(){
        global $twig, $config;
        $this->dVue['nbTours'] = $this->pendu->getNbTours();
        $this->dVue['nbFails'] = $this->pendu->getNbFails();
        $scientifique = $this->pendu->getScientifique();
        $dScientifique = [];
        $dScientifique['nom'] = $scientifique->getNom();
        $dScientifique['prenom'] = $scientifique->getPrenom();
        $dScientifique['sexe'] = $scientifique->getSexe()->getLibelle();
        $dScientifique['thematique'] = $scientifique->getThematique()->getLibelle();
        $dScientifique['dateNaiss'] = $scientifique->getDateNaiss()->format('d/m/Y');
        $this->dVue['scientifique'] = $dScientifique;
        unset($_SESSION['pendu']);

        echo $twig->render($config['templates']['penduScore'], ["dVue" => $this->dVue]);
    }
}