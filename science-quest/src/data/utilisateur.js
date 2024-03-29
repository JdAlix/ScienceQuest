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
    async creerCompte(){
        const response = await fetch(`${REST_API}/utilisateur`,{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(this)
        })
        return new this.constructor(await response.json())
    }
    async creerInvite(){
        const response = await fetch(`${REST_API}/invite`,{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(this)
        })
        return new this.constructor(await response.json())
    }
    async connecter(){
        const response = await fetch(`${REST_API}/utilisateur/connexion`,{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(this)
        })
        const utilisateurConnecte=new this.constructor(await response.json())
        localStorage.setItem("utilisateurConnecte",JSON.stringify(utilisateurConnecte))
        return utilisateurConnecte;
    }
    static async deconnecter(){
        localStorage.removeItem("utilisateurConnecte")
    }
    static async utilisateurConnecte(){
        const utilisateur=JSON.parse(localStorage.getItem("utilisateurConnecte"))
        return new this(utilisateur)
    }
    static async utilisateurConnecteOuCreerInvite(){
        const utilisateur=JSON.parse(localStorage.getItem("utilisateurConnecte"))
        if(utilisateur==null){
            const invite=new this({"pseudo":"invitetest123123"})
            return await invite.creerInvite()
        }
        return new this(utilisateur)
    }
}

/* JSON de reference pour le get, et retour des autres fonctions
{"email":"amogus@amog.us", "pseudo":"amogus", "id":"2"}
*/

/* JSON de reference (creerCompte)
{"email":"amogus@amog.us", "pseudo":"amogus", "motDePasse":"hunter2"}
*/
/* JSON de reference (creerInvite)
{"pseudo":"amogus"}
*/
/* JSON de reference (connecter)
in : {"email":"amogus@amog.us", "motDePasse":"hunter2"}
out : {"email":"amogus@amog.us", "pseudo":"amogus", "id":"2"}
*/