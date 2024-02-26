```plantuml
@startuml

entity "Jeu" as jeu {
    <u>id : int
    nom : string
    nbrParties : int
}

entity "Scientifique" as scientifique {
    <u>id : int
    nom : string
    prenom : string
    photo : string
    dateNaissance : date
    descriptif : string
    ratioTrouvee : float
    #idThematique : int
    #idDifficulte : int
    #idSexe : int
}

entity "Thematique" as thematique {
    <u>id : int
    libelle : string
}

entity "Difficulte" as difficulte {
    <u>id : int
    libelle : string
}

entity "Sexe" as sexe {
    <u>id : int
    libelle : string
}

entity "Joueur" as joueur {
    <u>id : int
    pseudo : string
    #idPartie : int
}

entity "Utilisateur" as utilisateur {
    <u>#idJoueur : int
    email : string
    motDePasse : string
    pseudo : string
}

entity "Invite" as invite {
    <u>#idJoueur : int
    <u>idSession : int
}

entity "Partie" as partie {
    <u>id : int
    codeInvitation : string
    #idJeu : int
}

entity "Admin" as admin {
    <u>id : int
    email : string
    motDePasse : string
}

entity "Decouvrir" as decouvrir {
  <u>#idUtilisateur : int
  <u>#idScientifique : int
}

entity "Indice" as indice {
  <u>id : int
  indice : string
  #idScientifique : int
}

entity "Reponse" as reponse {
  <u>id : int
  reponse : string
  #idScientifique : int
  #idQuestion : int
}

entity "Question" as question {
  <u>id : int
  question : string
}

partie --> jeu
partie <-- joueur
invite --> joueur
utilisateur --> joueur
utilisateur --> scientifique
scientifique --> thematique
scientifique --> sexe
scientifique --> difficulte
scientifique --> indice
question --> reponse
reponse --> scientifique
decouvrir --> scientifique
decouvrir --> utilisateur
jeu --> scientifique
@enduml
