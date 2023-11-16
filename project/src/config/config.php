<?php
namespace config;

$config = [
    "rep" => __DIR__.'/../',
    "db" => ["dsn" => 'pgsql:host=localhost;port=5432;dbname=sae2a',
             "login" => 'postgres',
             "mdp" =>''],
    "vues" => ["index" => 'vues/index.php']
];
?>