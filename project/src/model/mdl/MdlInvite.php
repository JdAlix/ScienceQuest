<?php

namespace model;

class MdlInvite extends MdlBase{
    private InviteGateway $gw;

    public function __construct(){
        parent::__construct();
        $this->gw = new InviteGateway($this->con);
    }

    public function setPseudo(int $id, string $pseudo): Invite{
        $this->gw->setPseudo($id, $pseudo);
        return $this->getFromId($id);
    }

    public function insertInvite(string $pseudo, string $idSession): Invite{
        $id = $this->gw->insertInvite($pseudo, $idSession);
        return $this->getFromId($id);
    }

    public function getFromId(int $id): Invite{
        $row = $this->gw->getFromId($id);
        return new Invite($row['idjoueur'], $row['pseudo'], $row['idsession']);
    }
}