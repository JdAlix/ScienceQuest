<?php

namespace model;

class ModelAdmin {
    public static function isAdmin() {
        if(!isset($_SESSION['admin'])
            || !$_SESSION['admin']
            || !isset($_SESSION['email'])
            || $_SESSION['email'] == null) {
            return false;
        }

        return true;
    }

    public static function logout() {
        global $basePath;

        session_unset();
        session_destroy();
        $_SESSION = array();
        header("Location: .");
    }
}