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
        if (!isset($jeu) || $jeu == '' || !filter_var($jeu, FILTER_VALIDATE_INT)) {
            $dVueErreur[] = 'Aucun jeu selectionné';
            $jeu          = 0;
        }

        if (!isset($difficulty) || $difficulty == '' || !filter_var($difficulty, FILTER_VALIDATE_INT)) {
            $dVueErreur[] = "Aucune difficultée séléctionnée";
            $difficulty   = 0;
        }
    }
}
