import { REST_API } from "@/assets/const"
import { DataObject, PagedDataObject } from "./dataObject"

export class Kahoot extends DataObject{
    constructor(parsedJSON){
        super(parsedJSON)
    }
    static async obtenirQuestion(){
			return new this(JSON.parse(`
				{
					"question":"Qui a reçu le prix Nobel de chimie en 1911, pour avoir réussi à isoler un gramme de radium ?",
					"reponses":["Marie Curie","Einstein","Sophie Germain","Ada Lovelace"],
					"tempsLimite":${Date.now()+10000 /* maintenant + 10 secondes pour repondre*/}
				}
			`))
		}
		static async obtenirScore(){
			return new this(JSON.parse(`
				{
					"score":1337,
					"pointsGagne":100,
					"leaderboard":{"Moi":1337, "Titouan":320},
					"tempsLimite":${Date.now()+10000 /* maintenant + 10 secondes le temps de regarder les scores*/}
				}
			`))
		}
		static async obtenirSalleAttente(){
			return new this(JSON.parse(`
				{
					"joueurs":["Moi","Titouan"],
					"partieDemarree":true,
					"tempsLimite":${Date.now()+10000 /* maintenant + 1 seconde*/}
				}
			`))
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
/* JSON de reference (salleAttente)
{
  "joueurs":["Moi","Titouan"],
  "partieDemarree":true,
  "tempsLimite":${Date.now()+this.DEBUG_temps maintenant + 1 seconde}
}
*/