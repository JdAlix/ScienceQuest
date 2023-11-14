<?php
    require_once(__DIR__.'/vendor/autoload.php');
    require_once(__DIR__.'/config/config.php');


    use controller\FrontController;

    //twig
    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig   = new \Twig\Environment($loader, [
        'cache' => false,
    ]);

    //altorouter
    $router = new AltoRouter();
    $router->setBasePath('/public_html/ScienceQuest');


    $cont = new FrontController();
    

    

/*
try{
    $con=new model\Connection($dsn,$login,$mdp);

    $query = "SELECT * FROM categorie WHERE id=:id";


    echo $con->executeQuery($query, array(':id' => array(1, PDO::PARAM_INT) ) );

    $results=$con->getResults();
    Foreach ($results as $row)
        print $row['titre'];


}
catch( PDOException $Exception ) {
    echo 'erreur';
    echo $Exception->getMessage();}
*/