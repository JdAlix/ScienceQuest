<?php
namespace config;

class Validation
{
    public static function val_action($action)
    {
        if (!isset($action)) {
            throw new \Exception('pas d\'action');
        }
    }

    public static function val_form(string &$jeu, string &$difficulty, &$dVueErreur)
    {
        if ($jeu == '' || !filter_var($jeu, FILTER_SANITIZE_STRING)) {
            $dVueErreur[] = 'Aucun jeu selectionné';
            $jeu          = 0;
            throw new \Exception("Erreur jeu");
        }

        if ($difficulty == '' || !filter_var($difficulty, FILTER_VALIDATE_INT)) {
            $dVueErreur[] = "Aucune difficultée séléctionnée";
            $difficulty   = 0;
            throw new \Exception("Erreur difficulté");
        }
    }
}
