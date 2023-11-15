<?php

namespace model;

class ScientistGateway
{
    private $con;

    function __construct(Connection $co) {
        $this->con = $co;
    }

//    function findByName(string $name) :? Scientist {
//        $usr = null;
//    }
}