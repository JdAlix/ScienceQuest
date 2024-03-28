import { REST_API } from "@/assets/const"
import { DataObject, PagedDataObject } from "./dataObject"

export class ListeJeux extends DataObject{
    constructor(parsedJSON){
        super(parsedJSON)
    }
    static async get(){
      const response = await fetch(`${REST_API}/jeux`)
      return new this(await response.json())
    }
}



/* JSON de reference (get ListeJeux)
[ {
  "id" : 1,
  "nom" : "Qui-est-ce ?",
  "nbrParties" : 0,
  "links" : [ {
    "rel" : "self",
    "href" : "http://sae-java.alix-jdlm.fr/api/v1/jeux/1"
  } ]
}, {
  "id" : 2,
  "nom" : "Science Quizz",
  "nbrParties" : 0,
  "links" : [ {
    "rel" : "self",
    "href" : "http://sae-java.alix-jdlm.fr/api/v1/jeux/2"
  } ]
}, {
  "id" : 3,
  "nom" : "Pendu",
  "nbrParties" : 0,
  "links" : [ {
    "rel" : "self",
    "href" : "http://sae-java.alix-jdlm.fr/api/v1/jeux/3"
  } ]
} ]
*/

