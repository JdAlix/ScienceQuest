<?php

namespace model;

class GameGateway
{
    public static function getGames() : array
    {
        // TODO: implémenter requête SQL
        return array(
            0 => ["Qui-est-ce ?", "Le qui est-ce...."],
            1 => ["Kahoot", "Le Kahoot permet..."]
        );
    }
}