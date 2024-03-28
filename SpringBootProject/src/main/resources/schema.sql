-- Testé sous pgsql 15

DROP TABLE IF EXISTS Reponse CASCADE;
DROP TABLE IF EXISTS Question CASCADE;
DROP TABLE IF EXISTS Admin CASCADE;
DROP TABLE IF EXISTS Partie CASCADE;
DROP TABLE IF EXISTS Jeu CASCADE;
DROP TABLE IF EXISTS Decouvrir CASCADE;
DROP TABLE IF EXISTS Utilisateur CASCADE;
DROP TABLE IF EXISTS Invite CASCADE;
DROP TABLE IF EXISTS Joueur CASCADE;
DROP TABLE IF EXISTS Indice CASCADE;
DROP TABLE IF EXISTS Scientifique CASCADE;
DROP TABLE IF EXISTS Thematique CASCADE;
DROP TABLE IF EXISTS ThematiqueSelectionnee CASCADE;
DROP TABLE IF EXISTS PartieKahoot CASCADE;
DROP TABLE IF EXISTS QuestionPartieKahoot CASCADE;
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
                             idDifficulte integer REFERENCES Difficulte(id)
);


-- INDICE

CREATE TABLE Indice(
                       id SERIAL PRIMARY KEY,
                       libelle varchar(512) NOT NULL,
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
                        estValide boolean NOT NULL,
                        idQuestion integer REFERENCES Question(id),
                        idScientifique integer REFERENCES Scientifique(id)
);


-- ADMIN

CREATE TABLE Admin(
                      id SERIAL PRIMARY KEY,
                      email varchar(255) NOT NULL UNIQUE,
                      password varchar(255) NOT NULL
);

-- Jeu

CREATE TABLE Jeu(
                    id SERIAL PRIMARY KEY,
                    nom varchar(128) NOT NULL UNIQUE,
                    nbrParties integer NOT NULL DEFAULT 0
);


-- Partie

CREATE OR REPLACE FUNCTION make_uid() RETURNS text AS
'
    DECLARE
        new_uid text;
        done bool;
    BEGIN
        done := false;
        WHILE NOT done LOOP
            new_uid := UPPER(SUBSTRING(md5(''''||now()::text||random()::text) FOR 5));
            done := NOT exists(SELECT 1 FROM partie WHERE codeinvitation=new_uid);
        END LOOP;

        RETURN new_uid;
    END;
' LANGUAGE PLPGSQL VOLATILE;


CREATE TABLE Partie(
                       id SERIAL PRIMARY KEY,
                       codeInvitation varchar(5) UNIQUE DEFAULT make_uid(),
                       idJeu integer REFERENCES Jeu(id),
                       idDifficulte integer REFERENCES Difficulte(id),
                       status varchar(128),
                       dateCreation timestamp DEFAULT CURRENT_TIMESTAMP
);


-- Partie Kahoot
CREATE TABLE PartieKahoot(
                    idPartie integer PRIMARY KEY REFERENCES Partie(id)
);

-- JOUEUR

CREATE TABLE Joueur(
                       id SERIAL PRIMARY KEY,
                       idPartie integer REFERENCES Partie(id),
                       pseudo varchar(255) NOT NULL UNIQUE
);


-- Invite

CREATE TABLE Invite(
                       idJoueur integer PRIMARY KEY REFERENCES Joueur(id)
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

-- ThematiqueSelectionnee

CREATE TABLE ThematiqueSelectionnee(
                          idPartie integer REFERENCES Partie(id),
                          idThematique integer REFERENCES Thematique(id),
                          PRIMARY KEY (idPartie, idThematique)
);


-- QuestionPartieKahoot
CREATE TABLE QuestionPartieKahoot(
                          idPartieKahoot integer REFERENCES PartieKahoot(idPartie),
                          idQuestion integer REFERENCES Question(id),
                          PRIMARY KEY (idPartieKahoot, idQuestion)
);


-- TRIGGERS



CREATE OR REPLACE FUNCTION force_default_partie()
    RETURNS TRIGGER
AS '
    DECLARE
    BEGIN
        IF OLD.codeInvitation IS NULL THEN
            NEW.codeInvitation = make_uid();
        END IF;
        IF OLD.dateCreation IS NULL THEN
            NEW.dateCreation = CURRENT_TIMESTAMP;
        END IF;
        RETURN NEW;
    END;
    '
LANGUAGE plpgsql;

CREATE TRIGGER check_force_default_partie
    BEFORE INSERT
    ON Partie
    FOR EACH ROW
EXECUTE PROCEDURE force_default_partie();


-- INSERTS

-- Scientifiques
INSERT INTO Difficulte(libelle) VALUES ('Facile'),('Intermédiaire'),('Difficile');
INSERT INTO Thematique(libelle) VALUES ('Nucléaire'),('Mathématiques');
INSERT INTO Scientifique(nom, prenom, photo, dateNaissance, descriptif, ratioTrouvee, idThematique, idDifficulte, sexe)
VALUES
    ('Marie', 'Curie', '', CURRENT_DATE, 'desc', 0.50, 1, 1, 'F'),
    ('Albert', 'Einstein', '', CURRENT_DATE, 'desc', 0.7540, 2, 1, 'H'),
    ('Sophie', 'Germain', '', CURRENT_DATE, 'desc', 0.1432, 2, 2, 'F');

-- Jeu
INSERT INTO Jeu(nom) VALUES ('Qui-est-ce ?'),('Science Quizz'), ('Pendu');

-- Questions
INSERT INTO Question(question)
VALUES
    ('Qui a reçu le prix Nobel de chimie en 1911, pour avoir réussi à isoler un gramme de radium ?'),
    ('Quel mathématicien a dit : « Dieu existe, c’est les mathématiques » ?'),
    ('Quel mathématicienne utilisa comme nom d"emprunt « Antoine Auguste Le Blanc » ?');

-- Indices
INSERT INTO Indice (libelle, idscientifique) VALUES
                                                 ('Indice pour aider', 1),
                                                 ('S''appelle Marie', 1);

-- Réponses
INSERT INTO Reponse(reponse, estValide, idQuestion, idScientifique)
VALUES
    ('Marie Curie', true, 1, 1),
    ('Albert Einstein', false, 1, 2),

    ('Marie Curie',true, 2, 1),
    ('Albert Einstein',false, 2, 2),

    ('Sophie Germain', true, 3, 3);


-- Partie
INSERT INTO Partie(codeInvitation, idJeu) VALUES ('abcde', 1);

-- Utilisateurs
INSERT INTO Joueur(pseudo, idPartie) VALUES ('moi, le meilleur joueur du monde', 1); --id = 1
INSERT INTO Utilisateur(idJoueur,email,password) VALUES (1, 'joueur','$2y$10$juGnlWC9cS19popEKLZsYeir0Jl39k6hDl0dpaCix00FDcdiEbtmS');
-- mdp = test

-- Découvrir
INSERT INTO decouvrir(idUtilisateur,idScientifique) VALUES (1,1);

-- Admin
INSERT INTO Admin(id,email,password) VALUES (1, 'admin','$2y$10$juGnlWC9cS19popEKLZsYeir0Jl39k6hDl0dpaCix00FDcdiEbtmS');
-- mdp = test