import { REST_API } from "@/assets/const"
import { DataObject, PagedDataObject } from "./dataObject"

export class Thematique extends DataObject{
    constructor(parsedJSON){
        super(parsedJSON)
    }
}

export class Thematiques extends PagedDataObject{
    constructor(parsedJSON){
        super(parsedJSON, Thematique)
    }
    static async getPage(pageNb=0, size=0){
        let params=""
        if(size>0){
          params+=`&size=${size}`
        }
        const response = await fetch(`${REST_API}/thematiques?page=${pageNb}${params}`)
        return new this(await response.json())
    }
}



/* JSON de reference (Thematiques)
{
  "_links" : {
    "first" : {
      "href" : "http://sae-java.alix-jdlm.fr/api/v1/thematiques?page=0&size=2"
    },
    "self" : {
      "href" : "http://sae-java.alix-jdlm.fr/api/v1/thematiques?page=0&size=2"
    },
    "next" : {
      "href" : "http://sae-java.alix-jdlm.fr/api/v1/thematiques?page=1&size=2"
    },
    "last" : {
      "href" : "http://sae-java.alix-jdlm.fr/api/v1/thematiques?page=2&size=2"
    }
  },
  "page" : {
    "size" : 2,
    "totalElements" : 6,
    "totalPages" : 3,
    "number" : 0
  },
  "_embedded" : [ {
    "id" : 1,
    "libelle" : "Nucléaire"
  }, {
    "id" : 2,
    "libelle" : "Mathématiques"
  } ]
}
*/

