<script>
import { REST_API } from '@/assets/const';

export default{
    methods:{
        envoyerDonnees: function(event){
            const donnees=new FormData(formajouter)

            //todo mettre lien dans const
            //envoyer le form en JSON
            fetch(REST_API+"/thematiques", {method:"POST", body:JSON.stringify(Object.fromEntries(donnees)), headers: {"Content-Type": "application/json"}})
            //sans le JSON.stringify et Object.fromEntries ca fait une requete en Content-Disposition
        }
    }
}


//TODO: verifier si on est admin quand on entre dans la partie admin
</script>

<template>
<form id="formajouter" @submit.prevent>
    <div>
        <label for="libelle">Libelle</label>
        <input class="form-control" type="text" id="libelle" name="libelle"/>
    </div>
    
    <button v-on:click="envoyerDonnees" class="btn btn-primary">Ajouter</button>
</form>
</template>