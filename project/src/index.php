<html>

<body>
test

<?php
require_once(__DIR__.'/config/config.php');
require_once(__DIR__."/model/Connection.php");
try{
    $con=new Connection($dsn,$login,$mdp);

    $query = "SELECT * FROM categorie WHERE id=:id";


    echo $con->executeQuery($query, array(':id' => array(1, PDO::PARAM_INT) ) );

    $results=$con->getResults();
    Foreach ($results as $row)
        print $row['titre'];


}
catch( PDOException $Exception ) {
    echo 'erreur';
    echo $Exception->getMessage();}
?>

</body>
</html>