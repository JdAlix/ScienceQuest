<?php
    require_once(__DIR__.'/vendor/autoload.php');
    require_once(__DIR__.'/config/config.php');

    use controller\FrontController;
    use Twig\Environment;
    use Twig\Loader\FilesystemLoader;

//twig
    $loader = new FilesystemLoader('templates');
    $twig   = new Environment($loader, [
        'cache' => false,
    ]);

    $dVueErreur = array();
    $basePath = preg_replace('/\/index.php/i', '', $_SERVER['PHP_SELF']);

    $cont = new FrontController();
    