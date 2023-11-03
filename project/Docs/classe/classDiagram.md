```plantuml 

@startuml "BDD"

class Scientifique{
    nom : String
    prenom : String
    photo : Raw
    dateNaissance : Date
    descriptif : String
    ratioTrouvee : Float
}

class Thematique{
    libelle : String
}

class "Difficulte"{
    libelle: String
}

class Indice{
    indice : String
}

Scientifique "*" --> "1..*" Thematique
Scientifique "*" --> "1" "Difficulte"
Scientifique "*" <-- "*"Utilisateur : A découvert <
Scientifique .. Admin : Gérer <
Scientifique "1" --> "*" Indice


class Question{
    question : String
}

class Reponse{
    reponse : String
}

Question "1" --> "1..*" Reponse
Reponse "0..*" --> "1" Scientifique

class Joueur{
    pseudo : String {unique}
}

class Utilisateur{
    email : String {unique}
    motDePasse : Hash
}

class Invite{
    idSession : Integer
}

Joueur <|-- Utilisateur
Joueur <|-- Invite

class Admin{
    email : String {unique}
    motDePasse : Hash
}

class Jeu{
    nom : String
    nbrParties : Integer
}

Jeu .. Scientifique  : Accède >

class Partie{
    codeInvitation : String
}

Partie "*" --> "1" Jeu

Partie "1" --> "1..*" Joueur

@enduml
```