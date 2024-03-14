<script>
import { REST_API } from '@/assets/const';

export default{
    props:["donnee", "endpoint"], //format : {"nomColonne":"typeDonnee", ...} => {"nom":"text", "desc":"text", ...}
    methods:{
        envoyerDonnees: function(event){
            const donnees=new FormData(formajouter)

            //envoyer le form en JSON
            fetch("localhost"+"/"+this.endpoint, {method:"POST", body:JSON.stringify(Object.fromEntries(donnees)), headers: {"Content-Type": "application/json"}})
            //sans le JSON.stringify et Object.fromEntries ca fait une requete en Content-Disposition
        }
    }
}
</script>

<template>
<form id="formajouter" @submit.prevent>
    <div>
        <fieldset v-for="nomColonne in Object.keys(donnee??{})">
            <label :for="nomColonne+'_temp_add_form'">{{nomColonne}}</label>
            <input class="form-control" :type="donnee[nomColonne]" :id="nomColonne+'_temp_add_form'" :aria-label="nomColonne"/>
        </fieldset>
    </div>
    
    <button v-on:click="envoyerDonnees" class="btn btn-primary">Ajouter</button>
</form>
</template>