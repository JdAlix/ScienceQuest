<?php
namespace config;

$config = [
    "rep" => __DIR__.'/../',

    "db" => ["dsn" => 'pgsql:host=localhost;dbname=postgres',
             "login" => 'postgres',
             "mdp" => 'root'],
    "templates" => ["index" => 'vues/index.php',
        "pseudo" => 'pseudo.html',
        "jouer" => "jouer.html",
        "pendu" => "pendu.html",
        "penduScore" => 'penduScore.html',
        "scienceQuizz" => 'scienceQuizz.html',
        "scienceQuizzReponse" => 'scienceQuizzReponse.html',
        "scienceQuizzRecap" => 'scienceQuizzRecap.html',]
];