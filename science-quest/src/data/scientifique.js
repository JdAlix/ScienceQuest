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
    static async getPage(pageNb, size=0){
        let sizeParam=""
        if(size>=0){
            sizeParam=`&size=${size}`
        }
        const response = await fetch(`${REST_API}/scientifiques?page=${pageNb}${sizeParam}`)
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