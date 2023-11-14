<?php
namespace controller;
use Exception;

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
						echo "accueil admin";exit;
					//	echo $twig->render('admin/accueil.html');
						break;
					case 'stats':
						echo "stats admin";exit;
					//	echo $twig->render('admin/stats.html');
						break;
					case 'ajouterScientifiques':
						echo "page ajout scientifiques admin";exit;
					//	echo $twig->render('admin/ajouter.html');
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
		//verifier si l'utilisateur est connecté mais pas admin
		if(isset($_SESSION["isLogged"])){
			if($_SESSION["isLogged"]==true) {
				//dire acces interdit au non admins
				array_push($dVueEreur, "Erreur 403 : Acces interdit");
				echo $twig->render('erreur.html', ['dVueErreur' => $dVueErreur]);
				exit(0);
			}
		} 
		//renvoyer a la page de connexion pour les non connectés
		echo $twig->render('login.html');
		exit(0);
	}
}
?>