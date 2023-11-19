<?php
namespace config;

$config = [
    "rep" => __DIR__.'/../',
    "db" => ["dsn" => 'pgsql:host=localhost;port=5432;dbname=sae2a',
             "login" => 'postgres',
             "mdp" =>''],
    "templates" => ["index" => 'vues/index.php',
               "pseudo" => 'pseudo.html',
               "pendu" => "pendu.html",
               "penduScore" => 'penduScore.html']
];
?>