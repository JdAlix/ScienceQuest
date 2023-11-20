<?php
namespace config;

$config = [
    "rep" => __DIR__.'/../',
    "db" => ["dsn" => 'pgsql:host=localhost;dbname=dbrebeuret',
             "login" => 'rebeuret',
             "mdp" => 'achanger'],
    "templates" => ["index" => 'vues/index.php',
               "pseudo" => 'pseudo.html',
               "pendu" => "pendu.html",
               "penduScore" => 'penduScore.html']
];

?>