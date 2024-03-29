export class DataObject{
    constructor(parsedJSON){
        //mettre les données du json directement dans l'objet
        Object.keys(parsedJSON).forEach(dataName=>this[dataName]=parsedJSON[dataName])
        //mettre les alias ici
        //ex : l'API change _embedded en _objectList mais que l'ancien code utilisait _embedded
        //this._objectList = this._embedded

        //ne pas autoriser les messages d'erreur, on va plutot lancer une exception
        if(this.error){
            throw this.error + " : " + this.message 
        }
    }
}

export class PagedDataObject extends DataObject{
    constructor(parsedJSON, dataObject){
        super(parsedJSON)
        //mettre objets correspondant dans la liste (ex : new Scientifique(obj) dans Scientifiques)
        this._embedded=this._embedded.map(obj=>new dataObject(obj))
    }
}