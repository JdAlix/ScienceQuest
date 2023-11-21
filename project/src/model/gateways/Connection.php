<?php

namespace model;

use Exception;
use PDO;
use PDOException;
use PDOStatement;

class Connection extends PDO {

    private PDOStatement $stmt;

    public function __construct(string $dsn, string $username, string $password) {
        try {
            parent::__construct($dsn, $username, $password);
            $this->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        } catch (PDOException $e) {
            die("Connection failed: " . $e->getMessage());
        }
    }


    /** *
     * @param string $query
     * @param array $params
     * @return bool|null Returns `true` on success, `false` otherwise
     */

    /**
     * @param string $query
     * @param array $params
     * @return PDOStatement|false Returns `PDOStatement` on success, `false` otherwise
     * @throws Exception
     */
    public function executeQuery(string $query, array $params = [])
    {
        try {
            $stmt = $this->prepare($query);

            foreach ($params as $param => $value) {
                $stmt->bindValue($param, $value[0], $value[1]);
            }

            $stmt->execute();
            $this->stmt = $stmt;

            return $stmt;

        } catch (PDOException $e) {
            throw new Exception($e->getMessage());
        }
    }

    public function getResults() : array {
        return $this->stmt->fetchall();
    }

    public function getOneResult() {
        return $this->stmt->fetch();
    }

}