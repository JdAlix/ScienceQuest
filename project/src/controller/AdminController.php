<?php
namespace controller;
use Exception;
use model\MdlDifficulte;
use model\MdlScientifique;
use model\MdlSexe;
use model\MdlThematique;
use model\Scientifique;

//gerer la connexion des admins
class AdminController {
	public function __construct(string $action){
		global $twig;
		//on initialise un tableau d'erreur pour etre utilisé par la vue erreur
		$dVueEreur = [];
		
		//verifier si l'utilisateur est connecté et admin
		if(isset($_SESSION["isAdmin"])){
			if($_SESSION["isAdmin"]==true){
			//donner la page admin a l'admin
			try {
				switch($action) {
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
					//mauvaise action
					default:
						$dVueErreur[] = "Erreur d'appel php";
						echo $twig->render('accueil.html', ['dVueErreur' => $dVueErreur]);
						break;
				}
			} catch (\PDOException $e) {
				$dVueErreur[] = 'Erreur avec la base de données !';
				echo $twig->render('erreur.html', ['dVueErreur' => $dVueErreur]);
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