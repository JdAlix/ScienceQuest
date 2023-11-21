<?php
namespace controller;
use config\Validation;
use Exception;
use PDOException;
use model\MdlDifficulte;
use model\MdlScientifique;
use model\MdlSexe;
use model\MdlThematique;
use model\Scientifique;

//gerer la connexion des admins
class AdminController {
	//public function __construct(array $params)
    //{
//
    //    //verifier si l'utilisateur est connecté et admin
    //    if (isset($_SESSION["isAdmin"])) {
    //        if ($_SESSION["isAdmin"] == true) {
    //        } else if (isset($_SESSION["isLogged"])) {
    //            //verifier si l'utilisateur est connecté mais pas admin
    //            if ($_SESSION["isLogged"] == true) {
    //                exit(0);
    //            }
    //        } else {
    //            //renvoyer a la page de connexion pour les non connectés
    //            echo '<meta http-equiv="refresh" content="0; url=login">';
    //        }
    //    }
    //}

    public function notLogged(array $params) {
        global $twig;
        //dire acces interdit aux non admins
        $dVueErreur[] = 'Erreur 403 : Accès interdit !';
        echo $twig->render('erreur.html', ['dVueErreur' => $dVueErreur]);
    }
    public function stats(array $params) {
        global $twig;

        echo $twig->render('admin/stats.html');
    }
    public function ajouterScientifiques(array $params) {
        global $twig;

        $sexe = new MdlSexe();
        $theme = new MdlThematique();
        $diff = new MdlDifficulte();
        $scient=null;
        if(!empty($_POST)){
            $id=0;
            if(isset($_GET["id"])){
                $id=intval($_GET["id"]);
            }
            $sci = new Scientifique(
                $id,
                $_POST["name"],
                $_POST["prenom"],
                $_POST["url"],
                \DateTime::createFromFormat("Y-m-d", $_POST["date"]),
                $_POST["description"],
                0,
                $theme->getFromId(intval($_POST["theme"])),
                $diff->getFromId(intval($_POST["difficulte"])),
                $sexe->getFromId(intval($_POST["sexe"]))
            );
            $mdlsci=new MdlScientifique();
            if(isset($_GET["id"])){
                $mdlsci->editScientifique($sci);
            } else {
                $mdlsci->addScientifique($sci);
            }
        }
        if(isset($_GET["id"])){
            $scient=new MdlScientifique();
            $scient=$scient->getScientifique($_GET["id"]);
        }

        echo $twig->render('admin/ajouterScientifiques.html',['sexe' => $sexe->getAll(), 'themes' => $theme->getAll(), 'difficultes' => $diff->getAll(), 'scientifique' => $scient]);
    }
    public function listeScientifique() {
       global $twig;

       $ms = new MdlScientifique();
        if (!isset($params['id'])) {
            $page = 1;
        } else {
            $page = Validation::valPosInt($params['id']);
        }
        $dVue['listeScientifiques'] = $ms->getScientifiquesParPage($page);
        $dVue['pageMax'] = $ms->getMaxPages();
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
        echo $twig->render('admin/listeScientifiques.html',['dVue' => $dVue]);
    }


}

?>