Jeu(\_id\_, nom, nbrParties)
id : clef primaire de la table Jeu

Scientifique(\_id\_, nom, prenom, photo, dateNaissance, descriptif, ratioTrouvee, #idThematique, #idDifficulte)
id : clef primaire de la table Scientifique
#idThematique clef étrangère en référence à id de la table Thematique
#idDifficulte clef étrangère en référence à id de la table Difficulté

Thematique(\_id\_, libelle)
id : clef primaire de la table Thematique

Difficulté(\_id\_, libelle)
id : clef primaire de la table Diffculté

Joueur(\_id\_, pseudo)
id:clef primaire de la table Joueur

Utilisateur(\_#idJoueur\_, email, motDePasse, pseudo)
idJoueur : clef primaire de la table Joueur
#idJoueur clef étrangère en référence à id de la table Joueur

Invite(\_#idJoueur\_, idSession)
idJoueur : clef primaire de la table Joueur
#idJoueur clef étrangère en référence à id de la table Joueur

Partie(\_id\_, codeInvitation, #idJoueur, #idJeu)
id: clef primaire de la table Partie
#idJoueur clef étrangère en référence à id de la table Joueur
#idJeu clef étrangère en référence à id de la table Jeu

Admin(\_#id\_, email, motDePasse)
id : clef primaire de la table Admin

Decouvrir(\_#idUtilisateur\_, \_#idScientifique\_)
idUtilisateur, idScientifique : clef primaire de la table découvrir
#idUtilisateur : clef étangère en référence à idJoueur de la table Utilisateur
#idScientifique : clef étrangère en référence à id de la table Scientifique

Indice(\_id\_, indice, #idScientifique)
id : clef primaire de la table Indice
#idScientifique : clef étrangère en référence à id de la table Scientifique

Reponse(\_id\_, reponse, #idScientifique, #idQuestion)
id : clef primaire de la table Reponse
#idScientifique : clef étrangère en référence à id de la table Scientifique
#idQuestion : clef étrangère en référence à id de la table Question

Question(\_id\_, question)
id : clef primaire de la table Question