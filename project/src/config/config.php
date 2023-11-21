<?php
namespace config;

$config = [
    "rep" => __DIR__.'/../',

    "db" => ["dsn" => 'pgsql:host=localhost;dbname=sae2a',
             "login" => 'postgres',
             "mdp" => 'SKf43V4hmD7a'],
    "templates" => ["index" => 'vues/index.php',
        "pseudo" => 'pseudo.html',
        "jouer" => "jouer.html",
        "pendu" => "pendu.html",
        "penduScore" => 'penduScore.html',
        "scienceQuizz" => 'scienceQuizz.html',
        "scienceQuizzReponse" => 'scienceQuizzReponse.html',
        "scienceQuizzRecap" => 'scienceQuizzRecap.html',]
];