import { REST_API } from "@/assets/const"
import { DataObject, PagedDataObject } from "./dataObject"

export class PartieInvite extends DataObject{
    constructor(parsedJSON){
        super(parsedJSON)
    }
	async creerInvite(){
        const response = await fetch(`${REST_API}/invite`,{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(this)
        })
        return new this(await response.json())
    }
}
export class Partie extends DataObject{
    constructor(parsedJSON){
        super(parsedJSON)
    }
	async creerPartie(){
        const response = await fetch(`${REST_API}/partie`,{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(this)
        })
        return new this(await response.json())
    }
	static async rejoindrePartie(codeInvitation, idJoueur){
		const response = await fetch(`${REST_API}/partie/${codeInvitation}`,{
            method:"PUT",
            headers:{"Content-Type":"application/json"},
            body:{"idJoueur":idJoueur}
        })
        return new this(await response.json())
	}
}



/* JSON de reference (creerInvite)
in : {pseudo: "...."}
out : {id: ?, pseudo: "?"}
*/

/* JSON de reference (creerPartie)
in:
{
    "idJeu": 1,
    "idJoueur": 1,
    "thematiques": [1],
    "idDifficulte": 1
}
out:
{
    "id": 3,
    "codeInvitation": "44122",
    "joueurs": [
        {
            "id": 1,
            "pseudo": "moi, le meilleur joueur du monde"
        }
    ],
    "jeu": {
        "id": 1,
        "nom": "Qui-est-ce ?",
        "nbrParties": 0
    },
    "thematiques": [
        {
            "id": 1,
            "libelle": "Nucléaire"
        },
        {
            "id": 2,
            "libelle": "Mathématiques"
        }
    ],
    "difficulte": {
        "id": 3,
        "libelle": "Difficile"
    }
}
*/