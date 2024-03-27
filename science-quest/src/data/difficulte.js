import { REST_API } from "@/assets/const"
import { DataObject, PagedDataObject } from "./dataObject"

export class Difficulte extends DataObject{
    constructor(parsedJSON){
        super(parsedJSON)
    }
}

export class Difficultes extends PagedDataObject{
    constructor(parsedJSON){
        super(parsedJSON, Difficulte)
    }
    static async getPage(pageNb=0, size=0){
        let params=""
        if(size>0){
          params+=`&size=${size}`
        }
        const response = await fetch(`${REST_API}/Difficultes?page=${pageNb}${params}`)
        return new this(await response.json())
    }
}



/* JSON de reference (Difficultes)
{
  "_links" : {
    "first" : {
      "href" : "http://sae-java.alix-jdlm.fr/api/v1/difficultes?page=0&size=2"
    },
    "self" : {
      "href" : "http://sae-java.alix-jdlm.fr/api/v1/difficultes?page=0&size=2"
    },
    "next" : {
      "href" : "http://sae-java.alix-jdlm.fr/api/v1/difficultes?page=1&size=2"
    },
    "last" : {
      "href" : "http://sae-java.alix-jdlm.fr/api/v1/difficultes?page=1&size=2"
    }
  },
  "page" : {
    "size" : 2,
    "totalElements" : 3,
    "totalPages" : 2,
    "number" : 0
  },
  "_embedded" : [ {
    "id" : 1,
    "libelle" : "Facile"
  }, {
    "id" : 2,
    "libelle" : "Intermédiaire"
  } ]
}
*/

