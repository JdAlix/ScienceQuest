**Jeu(<ins>id</ins>, nom, nbrParties)**
- *id* : clef primaire de la table Jeu

**Scientifique(<ins>id</ins>, nom, prenom, photo, dateNaissance, descriptif, ratioTrouvee, sexe, #idThematique, #idDifficulte)**
- *id* : clef primaire de la table Scientifique
- *#idThematique* clef étrangère en référence à *id* de la table Thematique
- *#idDifficulte* clef étrangère en référence à *id* de la table Difficulté

**Thematique(<ins>id</ins>, libelle)**
- *id* : clef primaire de la table Thematique

**Difficulté(<ins>id</ins>, libelle)**
- *id* : clef primaire de la table Difficulté

**Joueur(<ins>id</ins>, pseudo, #idPartie)**
- *id* : clef primaire de la table Joueur
- *#idPartie* : clef étrangère en référence à *id* de la table Partie

**Utilisateur(<ins>#idJoueur</ins>, email, motDePasse, pseudo)**
- *idJoueur* : clef primaire de la table Joueur
- *#idJoueur* : clef étrangère en référence à *id* de la table Joueur

**Invite(<ins>#idJoueur</ins>)**
- *idJoueur* : clef primaire de la table Joueur
- *#idJoueur* : clef étrangère en référence à *id* de la table Joueur

**Partie(<ins>id</ins>, codeInvitation, #idJeu)**
- *id* : clef primaire de la table Partie
- *#idJeu* : clef étrangère en référence à *id* de la table Jeu

**Admin(<ins>#id</ins>, email, motDePasse)**
- *id* : clef primaire de la table Admin

**Decouvrir(<ins>#idUtilisateur</ins>, <ins>#idScientifique</ins>)**
- *idUtilisateur*, *idScientifique* : clef primaire de la table découvrir
- *#idUtilisateur* : clef étangère en référence à *idJoueur* de la table Utilisateur
- *#idScientifique* : clef étrangère en référence à *id* de la table Scientifique

**Indice(<ins>id</ins>, indice, #idScientifique)**
- *id* : clef primaire de la table Indice
- *#idScientifique* : clef étrangère en référence à *id* de la table Scientifique

**Reponse(<ins>id</ins>, reponse, #idScientifique, #idQuestion)**
- *id* : clef primaire de la table Reponse
- *#idScientifique* : clef étrangère en référence à *id* de la table Scientifique
- *#idQuestion* : clef étrangère en référence à *id* de la table Question

**Question(<ins>id</ins>, question)**
- *id* : clef primaire de la table Question