import { REST_API } from "@/assets/const"
import { DataObject, PagedDataObject } from "./dataObject"

export class Utilisateur extends DataObject{
    constructor(parsedJSON){
        super(parsedJSON)
    }
    static async get(id){
        const response = await fetch(`${REST_API}/utilisateur/${id}`)
        return new this(await response.json())
    }
    async register(){
        const response = await fetch(`${REST_API}/utilisateur`,{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(this)
        })
        return new this(await response.json())
    }
    async login(){
        const response = await fetch(`${REST_API}/utilisateur/connexion`, {method:"POST" ,headers:{"Content-Type":"application/json"}, body:this})
        return new this(await response.json())
    }
}

//TODO : JSON de reference pour le get

/* JSON de reference (register)
{"email":"amogus@amog.us", "pseudo":"amogus", "motDePasse":"hunter2"}
*/
/* JSON de reference (login)
{"email":"amogus@amog.us", "motDePasse":"hunter2"}
*/