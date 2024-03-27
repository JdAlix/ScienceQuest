import { REST_API } from "@/assets/const"
import { DataObject, PagedDataObject } from "./dataObject"

export class Scientifique extends DataObject{
    constructor(parsedJSON){
        super(parsedJSON)
        //exemple d'alias pour le pendu
        this.nomComplet = this.nomComplet ?? this.nom + " " + this.prenom
    }
    static async get(id){
        const response = await fetch(`${REST_API}/scientifiques/${id}`)
        return new this(await response.json())
    }
}

export class Scientifiques extends PagedDataObject{
    constructor(parsedJSON){
        super(parsedJSON, Scientifique)
    }
    static async getPage(pageNb, size=0, thematiqueId=-1, difficulteId=-1){
        let params=""
        if(size>0){
          params+=`&size=${size}`
        }
        if(thematiqueId>-1){
          params+=`&thematiqueId=${thematiqueId}`
        }
        if(difficulteId>-1){
          params+=`&difficulteId=${difficulteId}`
        }
        const response = await fetch(`${REST_API}/scientifiques?page=${pageNb}${params}`)
        return new this(await response.json())
    }
}

export class ScientifiqueIndice extends DataObject{
    constructor(parsedJSON){
        super(parsedJSON)
    }
}

export class ScientifiqueIndices extends PagedDataObject{
    constructor(parsedJSON){
        super(parsedJSON, ScientifiqueIndice)
    }
    static async getPage(idScientifique, size){
        const response = await fetch(`${REST_API}/scientifiques/${idScientifique}/indices`)
        return new this(await response.json())
    }
}

/* JSON de reference (Scientifique)
{
  "id" : 1,
  "difficulte" : {
    "id" : 1,
    "libelle" : "Facile"
  },
  "thematique" : {
    "id" : 1,
    "libelle" : "Nucléaire"
  },
  "pathToPhoto" : "",
  "nom" : "Marie",
  "prenom" : "Curie",
  "descriptif" : "desc",
  "dateNaissance" : "2024-03-01T00:00:00.000+00:00",
  "sexe" : "F",
  "ratioTrouve" : 0.5,
  "_links" : {
    "indices" : {
      "href" : "http://sae-java.alix-jdlm.fr/api/v1/scientifiques/1/indices"
    },
    "self" : {
      "href" : "http://sae-java.alix-jdlm.fr/api/v1/scientifiques/1"
    }
  }
}

*/

/* JSON de reference (Scientifiques / Page de scientifiques)

{
  "_links" : {
    "first" : {
      "href" : "http://sae-java.alix-jdlm.fr/api/v1/scientifiques?page=0&size=2"
    },
    "self" : {
      "href" : "http://sae-java.alix-jdlm.fr/api/v1/scientifiques?page=0&size=2"
    },
    "next" : {
      "href" : "http://sae-java.alix-jdlm.fr/api/v1/scientifiques?page=1&size=2"
    },
    "last" : {
      "href" : "http://sae-java.alix-jdlm.fr/api/v1/scientifiques?page=1&size=2"
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
    "difficulte" : {
      "id" : 1,
      "libelle" : "Facile"
    },
    "thematique" : {
      "id" : 1,
      "libelle" : "Nucléaire"
    },
    "pathToPhoto" : "",
    "nom" : "Marie",
    "prenom" : "Curie",
    "descriptif" : "desc",
    "dateNaissance" : "2024-03-01T00:00:00.000+00:00",
    "sexe" : "F",
    "ratioTrouve" : 0.5,
    "_links" : {
      "indices" : {
        "href" : "http://sae-java.alix-jdlm.fr/api/v1/scientifiques/1/indices"
      },
      "self" : {
        "href" : "http://sae-java.alix-jdlm.fr/api/v1/scientifiques/1"
      }
    }
  }, {
    "id" : 2,
    "difficulte" : {
      "id" : 1,
      "libelle" : "Facile"
    },
    "thematique" : {
      "id" : 2,
      "libelle" : "Mathématiques"
    },
    "pathToPhoto" : "",
    "nom" : "Albert",
    "prenom" : "Einstein",
    "descriptif" : "desc",
    "dateNaissance" : "2024-03-01T00:00:00.000+00:00",
    "sexe" : "H",
    "ratioTrouve" : 0.754,
    "_links" : {
      "indices" : {
        "href" : "http://sae-java.alix-jdlm.fr/api/v1/scientifiques/2/indices"
      },
      "self" : {
        "href" : "http://sae-java.alix-jdlm.fr/api/v1/scientifiques/2"
      }
    }
  } ]
}

*/

/* JSON de reference (ScientifiqueIndices)

{
  "_links" : {
    "self" : {
      "href" : "http://sae-java.alix-jdlm.fr/api/v1/scientifiques/1/indices"
    }
  },
  "_embedded" : [ {
    "id" : 1,
    "libelle" : "Indice pour aider",
    "scientifique" : {
      "id" : 1
    }
  }, {
    "id" : 2,
    "libelle" : "S'appelle Marie",
    "scientifique" : {
      "id" : 1
    }
  } ]
}


*/