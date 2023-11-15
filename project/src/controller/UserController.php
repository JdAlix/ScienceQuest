<?php

namespace controller;

use model\Connection;
use model\GameGateway;

class UserController {
    public function CreateParty() {
        global $twig;

        $dVueCreate = \model\GameGateway::getGames();
        echo $twig->render('create.html', ['dVueCreate' => $dVueCreate]);
    }

    public function JoinParty() {
        global $twig;
        global $base;
        global $login;
        global $mdp;

        $con = new Connection($base, $login, $mdp);

        $gg = new GameGateway($con);
        if(!isset($_REQUEST['code'])) {
            echo $twig->render('join.html');
        } elseif (empty($_REQUEST['code']) || !sizeof($gg->getGameByCode($_REQUEST['code']))) {
            $dErreur[] = 'Code de partie invalide';
            echo $twig->render('join.html',['dErreur' => $dErreur]);
        } else {
            // rejoindre la partie
        }
    }

    public function accueil(array $params) {
        global $twig;
        global $dVue;

        echo $twig->render('accueil.html',['dVue' => $dVue]);
    }
}