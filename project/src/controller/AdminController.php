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
	public function __construct(array $params){
		global $twig;
        global $dVue;
		//on initialise un tableau d'erreur pour être utilisé par la vue erreur
		$dVueErreur = [];
		
		//verifier si l'utilisateur est connecté et admin
		if(isset($_SESSION["isAdmin"])){
			if($_SESSION["isAdmin"]==true){
			//donner la page admin a l'admin
			try {
				switch($params['action']) {

					case '':
						echo $twig->render('admin/accueil.html');
						break;

					case 'stats':
						echo $twig->render('admin/stats.html');
						break;

					case 'ajouterScientifiques':
						$sexe = new MdlSexe();
						$theme = new MdlThematique();
						$diff = new MdlDifficulte();
						if(!empty($_POST)){
							$sci=new MdlScientifique();
							$sci->addScientifique(new Scientifique(0, 
							$_POST["name"],
							$_POST["prenom"],
							$_POST["url"],
							\DateTime::createFromFormat("Y-m-d",$_POST["date"]),
							$_POST["description"],
							0,
							$theme->getFromId(intval($_POST["theme"])),
							$diff->getFromId(intval($_POST["difficulte"])),
							$sexe->getFromId(intval($_POST["sexe"]))
						));
						}
						echo $twig->render('admin/ajouterScientifiques.html',['sexe' => $sexe->getAll(), 'themes' => $theme->getAll(), 'difficultes' => $diff->getAll()]);
						break;

                    case 'listeScientifiques':
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
                        break;

                    //mauvaise action
					default:
						$dVueErreur[] = "Erreur d'appel php";
						echo $twig->render('erreur.html', ['dVueErreur' => $dVueErreur]);
						break;
				}
			} catch (\Exception $e2) {
				$dVueErreur[] = 'Erreur inattendue !';
				echo $twig->render('erreur.html', ['dVueErreur' => $dVueErreur]);
			}
			}
		}
		else if(isset($_SESSION["isLogged"])){
			//verifier si l'utilisateur est connecté mais pas admin
			if($_SESSION["isLogged"]==true) {
				//dire acces interdit aux non admins
				$dVueErreur[] = 'Erreur 403 : Accès interdit !';
				echo $twig->render('erreur.html', ['dVueErreur' => $dVueErreur]);
				exit(0);
			}
		} else {
			//renvoyer a la page de connexion pour les non connectés
			echo '<meta http-equiv="refresh" content="0; url=login">';
		}
		exit(0);
	}
	
}




?>