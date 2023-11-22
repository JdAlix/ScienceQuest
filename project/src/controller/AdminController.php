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

    public function defaultAction(array $params) {
        global $twig;

        echo $twig->render('admin/accueil.html');
    }
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
            try{
                $this->verifierDonnees();
            } catch (Exception $ex){
                $dVueErreur[] = 'Erreur : '.$ex;
                echo $twig->render('erreur.html', ['dVueErreur' => $dVueErreur]);
                return;
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
    public function listeScientifiques(array $params) {
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


    private function verifierDonnees(){
        $sexe = new MdlSexe();
        $theme = new MdlThematique();
        $diff = new MdlDifficulte();
        $selectTheme=$theme->getFromId(intval($_POST["theme"]));
            $selectDiff=$diff->getFromId(intval($_POST["difficulte"]));
            $selectSexe=$sexe->getFromId(intval($_POST["sexe"]));
            //todo : verifier les données, mettre dans une fonction et try catch
            if(strlen($_POST["name"]) < 2){
                throw new Exception("nom trop court");
            }
            if(strlen($_POST["prenom"]) < 2){
                throw new Exception("prénom trop court");
            }
            if(empty($_POST["url"])){
                throw new Exception("pas de photo");
            }
            if(strlen($_POST["name"]) < 2){
                throw new Exception("nom trop court");
            }
            if(strlen($_POST["description"]) < 20){
                throw new Exception("description trop courte ");
            }
            if($selectTheme==null){
                throw new Exception("thematique inconnue");
            }
            if($selectSexe==null){
                throw new Exception("sexe inconnu");
            }
            if($selectDiff==null){
                throw new Exception("difficulté inconnue");
            }
    }
}

?>