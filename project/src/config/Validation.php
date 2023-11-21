<?php
namespace config;


use model\ConfigurationJeu;
use model\Joueur;
use model\MdlPendu;
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
            $dVueErreur[] = "Aucune difficulté séléctionnée";
            $difficulty   = 0;
            throw new ValidationException("Erreur difficulté");
        }
    }

    public static function valUserLogin(string $user, &$dVueErreur)
    {
        if ($user == '' || !filter_var($user, FILTER_SANITIZE_STRING)) {
            $dVueErreur[] = 'Identifiant invalide';
            $jeu          = 0;
            throw new ValidationException("Erreur identifiant");
        }
    }

    public static function valCodeInvitation(string $codeInvitation, &$dVueErreur): string
    {
        if($codeInvitation == ''){
            $dVueErreur[] = 'Code d\'invitation invalide';
            throw new ValidationException("Code d'invitation invalide");
        }
        return htmlspecialchars($codeInvitation);
    }

    public static function valPseudo(string &$pseudo, &$dVueErreur){
        $pseudo = trim($pseudo);
        $pseudo = htmlspecialchars($pseudo);
        $pseudo = filter_var($pseudo, FILTER_UNSAFE_RAW);
        if($pseudo == '' ){
            $dVueErreur[] = 'Pseudo invalide';
            throw new ValidationException("Pseudo invalide");
        }
        return $pseudo;
    }

    public static function valRole(&$role, &$dVueErreur): Joueur
    {
        if(! $role instanceof Joueur){
            $role = NULL;
            $dVueErreur[] = 'Role invalide';
            throw new ValidationException('Role invalide');
        }
        return $role;
    }

    public static function valConfigurationJeu($configurationJeu, &$dVueErreur): ConfigurationJeu
    {
        if(! $configurationJeu instanceof ConfigurationJeu){
            $role = NULL;
            $dVueErreur[] = 'Configuration du jeu invalide';
            throw new ValidationException('Configuration du jeu');
        }
        return $configurationJeu;
    }

    public static function valMdlPendu($pendu, &$dVueErreur): MdlPendu
    {
        if(! $pendu instanceof MdlPendu){
            $role = NULL;
            $dVueErreur[] = 'Erreur mauvais jeu en utilisation';
            throw new ValidationException('Erreur mauvais jeu en utilisation');
        }
        return $pendu;
    }

}
