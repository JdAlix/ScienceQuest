--Testé sous pgsql 15

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

DROP TABLE IF EXISTS Sexe;


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


-- SEXE

CREATE TABLE Sexe(
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
    idThematique integer REFERENCES Thematique(id),
    idDifficulte integer REFERENCES Difficulte(id),
    idSexe integer REFERENCES Sexe(id)
);


-- INDICE

CREATE TABLE Indice(
    id SERIAL PRIMARY KEY,
    libelle varchar(512) NOT NULL UNIQUE,
    idScientifique integer REFERENCES Scientifique(id)
);


--QUESTION

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
    motDePasse varchar(255) NOT NULL
);


-- JOUEUR

CREATE TABLE Joueur(
    id SERIAL PRIMARY KEY,
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
    motDePasse varchar(255) NOT NULL
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
    idJoueur integer REFERENCES Joueur(id),
    idJeu integer REFERENCES Jeu(id)
);