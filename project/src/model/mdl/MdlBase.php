<?php

namespace model;

abstract class MdlBase{
    protected Connection $con;

    public function __construct()
    {
        global $config;
        $this->con = new Connection($config["db"]["dsn"], $config["db"]["login"], $config["db"]["login"]);
    }
}