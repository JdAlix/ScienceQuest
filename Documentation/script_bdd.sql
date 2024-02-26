-- Testé sous pgsql 15

DROP TABLE IF EXISTS Reponse;

DROP TABLE IF EXISTS Question;

DROP TABLE IF EXISTS Admin;

DROP TABLE IF EXISTS Partie;

DROP TABLE IF EXISTS Jeu;

DROP TABLE IF EXISTS Decouvrir;

DROP TABLE IF EXISTS Utilisateur;

DROP TABLE IF EXISTS Invite;

DROP TABLE IF EXISTS Joueur;

DROP TABLE IF EXISTS Indice;

DROP TABLE IF EXISTS Scientifique;

DROP TABLE IF EXISTS Thematique;

DROP TABLE IF EXISTS Difficulte;


-- THEMATIQUE

CREATE TABLE Thematique(
    id SERIAL PRIMARY KEY,
    libelle varchar(128) NOT NULL UNIQUE
);


-- DIFFICULTE

CREATE TABLE Difficulte(
    id SERIAL PRIMARY KEY,
    libelle varchar(128) NOT NULL UNIQUE
);


-- SCIENTIFIQUE

CREATE TABLE Scientifique(
    id SERIAL PRIMARY KEY,
    nom varchar(128) NOT NULL,
    prenom varchar(128) NOT NULL,
    photo varchar(512) NOT NULL,
    dateNaissance date NOT NULL,
    descriptif text NOT NULL,
    ratioTrouvee numeric(5,4),
    sexe char(1) NOT NULL CHECK(sexe IN ('F', 'H')),
    idThematique integer REFERENCES Thematique(id),
    idDifficulte integer REFERENCES Difficulte(id),
);


-- INDICE

CREATE TABLE Indice(
    id SERIAL PRIMARY KEY,
    libelle varchar(512) NOT NULL UNIQUE,
    idScientifique integer REFERENCES Scientifique(id)
);

-- QUESTION

CREATE TABLE Question(
    id SERIAL PRIMARY KEY,
    question varchar(256) NOT NULL UNIQUE
);


-- REPONSE

CREATE TABLE Reponse(
    id SERIAL PRIMARY KEY,
    reponse varchar(255) NOT NULL,
    idQuestion integer REFERENCES Question(id),
    idScientifique integer REFERENCES Scientifique(id)
);


-- ADMIN

CREATE TABLE Admin(
    id SERIAL PRIMARY KEY,
    email varchar(255) NOT NULL UNIQUE,
    password varchar(255) NOT NULL
);


-- JOUEUR

CREATE TABLE Joueur(
    id SERIAL PRIMARY KEY,
    idPartie integer REFERENCES Partie(id),
    pseudo varchar(255) NOT NULL UNIQUE
);


-- Invite

CREATE TABLE Invite(
    idJoueur integer PRIMARY KEY REFERENCES Joueur(id),
    idSession varchar(255) NOT NULL UNIQUE
);


-- Utilisateur

CREATE TABLE Utilisateur(
    idJoueur integer PRIMARY KEY REFERENCES Joueur(id),
    email varchar(255) NOT NULL UNIQUE,
    password varchar(255) NOT NULL
);


-- Decouvrir

CREATE TABLE Decouvrir(
    idUtilisateur integer REFERENCES Utilisateur(idJoueur),
    idScientifique integer REFERENCES Scientifique(id),
    PRIMARY KEY (idUtilisateur, idScientifique)
);


-- Jeu

CREATE TABLE Jeu(
    id SERIAL PRIMARY KEY,
    nom varchar(128) NOT NULL UNIQUE,
    nbrParties integer NOT NULL DEFAULT 0
);


-- Partie

CREATE TABLE Partie(
    id SERIAL PRIMARY KEY,
    codeInvitation varchar(10) NOT NULL UNIQUE,
    idJeu integer REFERENCES Jeu(id)
);


-- INSERTS

-- Scientifiques
INSERT INTO Difficulte(libelle) VALUES ('Facile'),('Intermédiaire'),('Difficile');
INSERT INTO Thematique(libelle) VALUES ('Nucléaire'),('Mathématiques');
INSERT INTO Scientifique(nom, prenom, photo, dateNaissance, descriptif, ratioTrouvee, idThematique, idDifficulte, idSexe)
VALUES
    ('Marie', 'Curie', '', CURRENT_DATE, 'desc', 0, 1, 1, 'F'),
    ('Albert', 'Einstein', '', CURRENT_DATE, 'desc', 0, 2, 1, 'H'),
    ('Sophie', 'Germain', '', CURRENT_DATE, 'desc', 0, 2, 2, 'F');

-- Jeu
INSERT INTO Jeu(nom) VALUES ('Qui-est-ce ?'),('Science Quizz'), ('Pendu');

-- Questions
INSERT INTO Question(question)
VALUES
    ('Qui a reçu le prix Nobel de chimie en 1911, pour avoir réussi à isoler un gramme de radium ?'),
    ('Quel mathématicien a dit : « Dieu existe, c’est les mathématiques » ?'),
    ('Quel mathématicienne utilisa comme nom d"emprunt « Antoine Auguste Le Blanc » ?');

-- Réponses
INSERT INTO Reponse(reponse, idQuestion, idScientifique)
VALUES
    ('Marie Curie', 1, 1),
    ('Albert Einstein', 2, 2),
    ('Sophie Germain', 3, 3);

-- Utilisateurs
INSERT INTO Joueur(id,pseudo) VALUES (1337, 'moi, le meilleur joueur du monde');
INSERT INTO Utilisateur(idJoueur,email,password) VALUES (1337, 'joueur','$2y$10$juGnlWC9cS19popEKLZsYeir0Jl39k6hDl0dpaCix00FDcdiEbtmS');
-- mdp = test

INSERT INTO decouvrir(idUtilisateur,idScientifique) VALUES (1337,1);

INSERT INTO Admin(id,email,password) VALUES (1, 'admin','$2y$10$juGnlWC9cS19popEKLZsYeir0Jl39k6hDl0dpaCix00FDcdiEbtmS');
-- mdp = test
