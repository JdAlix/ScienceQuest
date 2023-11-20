<?php
    require_once(__DIR__.'/vendor/autoload.php');
    require_once(__DIR__.'/config/config.php');


    use controller\FrontController;

    //twig
    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig   = new \Twig\Environment($loader, [
        'cache' => false,
    ]);

    $dVueErreur = array();
    $basePath = preg_replace('/\/index.php/i', '', $_SERVER['PHP_SELF']);

    $cont = new FrontController();
    