import { REST_API } from "@/assets/const"
import { DataObject, PagedDataObject } from "./dataObject"
import { Utilisateur } from "./utilisateur"

export class Kahoot{
    constructor(codeInvitation){
		  this.codeInvitation=codeInvitation
      this.rejoindrePartie().then()
    }
	async obtenirSalleAttente(){
		const response=await fetch(`${REST_API}/partie/kahoot/${this.codeInvitation}/status`)
		return new KahootSalleAttente(await response.json())
  }
  async obtenirQuestion(){
		const response=await fetch(`${REST_API}/partie/kahoot/${this.codeInvitation}/question`)
		return new KahootQuestion(await response.json())
  }
  async repondreQuestion(id){
    const user = await Utilisateur.utilisateurConnecteOuCreerInvite()
    const response = await fetch(`${REST_API}/partie/kahoot/${this.codeInvitation}/reponse`,{
      method:"POST",
      headers:{"Content-Type":"application/json"},
      body:JSON.stringify({"idJoueur":user.id, "idReponse":id})
    })
    return null
  }
  async rejoindrePartie(){
    const user = await Utilisateur.utilisateurConnecteOuCreerInvite()
		const response = await fetch(`${REST_API}/partie/kahoot/${this.codeInvitation}`,{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify({"idJoueur":user.id})
        })
    return null
	}
  async demarrerPartie(){
		const response = await fetch(`${REST_API}/partie/kahoot/${this.codeInvitation}/demarrer`,{
            method:"POST",
            headers:{"Content-Type":"application/json"}
        })
    return null
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

/* JSON de reference (PartieDetails)
in: {"idJoueur": 0, "thematiques": [0,1,2,3], "idDifficulte": 0}

out:
{"id": 0, 
"codeInvitation": 0,
"joueurs": [
  {"id": 0, "pseudo": 0},
],
"thematiques": [
  {"id": 0, "libelle": 0},
],
"difficulte": {"id":0, "libelle": 0}
}
*/
export class KahootPartie extends DataObject{
	constructor(parsedJSON){
        super(parsedJSON)
    }
    async creerPartie(){
      const user = await Utilisateur.utilisateurConnecteOuCreerInvite()
      const response = await fetch(`${REST_API}/partie/kahoot`,{
        method:"POST",
        headers:{"Content-Type":"application/json"},
        //{"idJoueur": 0, "thematiques": [0,1,2,3], "idDifficulte": 0}
        body:JSON.stringify({"idJoueur":user.id, "thematiques":this.thematiques, "idDifficulte":this.idDifficulte})
      })
      return new this.constructor(await response.json())
    }
}

/* JSON de reference (question)
{
  "question":"Qui a reçu le prix Nobel de chimie en 1911, pour avoir réussi à isoler un gramme de radium ?",
  "reponses":["Marie Curie","Einstein","Sophie Germain","Ada Lovelace"],
  "tempsLimite":${Date.now()+this.DEBUG_temps maintenant + 10 secondes pour repondre}
}
*/
export class KahootQuestion extends DataObject{
	constructor(parsedJSON){
        super(parsedJSON)
        this.tempsLimite=new Date(this.tempsLimiteReponse).getTime()
  }
}

/* JSON de reference (score)
{
  "score":1337,
  "pointsGagne":100,
  "leaderboard":{"Moi":1337, "Titouan":320},
  "tempsLimite":${Date.now()+this.DEBUG_temps maintenant + 10 secondes le temps de regarder les scores}
}
*/