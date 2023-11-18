<?php
namespace config;
use model\ValidationException;

class Validation
{
    public static function val_action($action)
    {
        if (!isset($action)) {
            throw new ValidationException('pas d\'action');
        }
    }

    public static function val_form(string &$jeu, string &$difficulty, &$dVueErreur)
    {
        if ($jeu == '' || !filter_var($jeu, FILTER_SANITIZE_STRING)) {
            $dVueErreur[] = 'Aucun jeu selectionné';
            $jeu          = 0;
            throw new ValidationException("Erreur jeu");
        }

        if ($difficulty == '' || !filter_var($difficulty, FILTER_VALIDATE_INT)) {
            $dVueErreur[] = "Aucune difficultée séléctionnée";
            $difficulty   = 0;
            throw new ValidationException("Erreur difficulté");
        }
    }

    public static function valUserLogin(string &$user, &$dVueErreur)
    {
        if ($user == '' || !filter_var($user, FILTER_SANITIZE_STRING)) {
            $dVueErreur[] = 'Identifiant invalide';
            $jeu          = 0;
            throw new ValidationException("Erreur identifiant");
        }
    }

    public static function valCodeInvitation(string &$codeInvitation, &$dVueErreur){
        if($codeInvitation == ''){
            $dVueErreur[] = 'Code d\'invitation invalide';
            throw new ValidationException("Code d'invitation invalide");
        }
        return htmlspecialchars($codeInvitation);
    }

}
