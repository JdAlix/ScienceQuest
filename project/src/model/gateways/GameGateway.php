<?php

namespace model;

class GameGateway
{
    private $con;

    function __construct(Connection $co) {
        $this->con = $co;
    }
    public static function getGames() : array
    {
        // TODO: implémenter requête SQL
        return array(
            0 => ["Qui-est-ce ?", "Le qui est-ce...."],
            1 => ["Kahoot", "Le Kahoot permet..."]
        );
    }

    public function getGameByCode(int $code) : array {
        $query = 'SELECT * FROM Partie WHERE codeInvitation=:id';
        $this->con->executeQuery($query,array(
            ':id' => array($code,\PDO::PARAM_INT)
        ));
        return $this->con->getResults();
    }
}