import { REST_API } from "@/assets/const"
import { DataObject, PagedDataObject } from "./dataObject"

export class Kahoot{
    constructor(codeInvitation){
		this.codeInvitation=codeInvitation
    }
	async obtenirSalleAttente(){
		const response=await fetch(`${REST_API}/partie/kahoot/${this.codeInvitation}/status`)
		return new KahootSalleAttente(await response.json())
	}
}


/* JSON de reference (salleAttente)
{
  "joueurs":["Moi","Titouan"],
  "partieDemarree":true,
  "tempsLimite":${Date.now()+this.DEBUG_temps maintenant + 1 seconde}
}
*/
export class KahootSalleAttente extends DataObject{
	constructor(parsedJSON){
        super(parsedJSON)
		this.partieDemarree=this.status!="Pending"
		this.joueurs=this.scores.map(score=>score.joueur.pseudo)
		this.tempsLimite=Date.now()+1000
    }
}

/* JSON de reference (question)
{
  "question":"Qui a reçu le prix Nobel de chimie en 1911, pour avoir réussi à isoler un gramme de radium ?",
  "reponses":["Marie Curie","Einstein","Sophie Germain","Ada Lovelace"],
  "tempsLimite":${Date.now()+this.DEBUG_temps maintenant + 10 secondes pour repondre}
}
*/
/* JSON de reference (score)
{
  "score":1337,
  "pointsGagne":100,
  "leaderboard":{"Moi":1337, "Titouan":320},
  "tempsLimite":${Date.now()+this.DEBUG_temps maintenant + 10 secondes le temps de regarder les scores}
}
*/