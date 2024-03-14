<script>
import { REST_API } from '@/assets/const';

export default{
    props:["champs", "endpoint"], //format : {"nomColonne":"typeChamp", ...} => {"nom":"text", "desc":"text", ...}
    methods:{
        envoyerDonnees: function(event){
            const donnees=new FormData(formajouter)

            //envoyer le form en JSON
            fetch("localhost"+"/"+this.endpoint, {method:"POST", body:JSON.stringify(Object.fromEntries(donnees)), headers: {"Content-Type": "application/json"}})
            //sans le JSON.stringify et Object.fromEntries ca fait une requete en Content-Disposition
        },
        typeDeChamp: function(champ){ //TODO mettre cette fonction dans un fichier commun
            switch(typeof champ){
                case 'number':
                case 'bigint':
                    return "number"
                case 'string':
                    return this.estUneDate(champ) ? "date" : "text"
                case 'boolean':
                    return "checkbox"
                case 'symbol':
                case 'undefined':
                case 'object':
                case 'function':
                    return "hidden" //TODO : implementer le reste
            }
        },
        estUneDate: function(date) { //TODO mettre cette fonction dans un fichier commun
            return new Date(date) != "Invalid Date";
        },
    }
}
</script>

<template>
<form id="formajouter" @submit.prevent>
    <div>
        <fieldset v-for="nomColonne in Object.keys(champs??{})">
            <label v-show="typeDeChamp(champs[nomColonne])!='hidden'" :for="nomColonne+'_temp_add_form'">{{nomColonne}}</label>
            <input class="form-control" :type="typeDeChamp(champs[nomColonne])" :id="nomColonne+'_temp_add_form'" :aria-label="nomColonne" :name="nomColonne"/>
        </fieldset>
    </div>
    
    <button v-on:click="envoyerDonnees" class="btn btn-primary">Ajouter</button>
</form>
</template>