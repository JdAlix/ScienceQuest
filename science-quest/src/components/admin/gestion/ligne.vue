<script>
export default {
    props: ["champsInit"],
    data(){
        return{
            modeEdition:false, //vrai = remplacer les champs par des inputs

            champs:this.champsInit
        }
    },
    methods:{
        changerModeEdition: function(){
            this.modeEdition=!this.modeEdition
        },
        annuler: function(){
            //remettre les valeurs a 0
            this.champs=this.champsInit

            this.changerModeEdition()
        },
        sauverScientifique: function(){
            //TODO : comme dans ajout
            //TODO
            //fetch("localhost/api/v1/scientifiques", {method:"PUT", body:JSON.stringify(donnees)})
            console.log(this.champs)
            this.changerModeEdition()
        }
    }
}
</script>


<template>
    <tr v-if="!this.modeEdition">
        <td v-for="item in champs">
            <p>{{ item }}</p>
        </td>
        <td>
            <button class="btn-outline-secondary btn" v-on:click="changerModeEdition()">Modifier</button>
        </td>
    </tr>
    <tr v-if="this.modeEdition">
        
        <!-- TODO : changer le type si c'est un nombre, date (voir si on peut parse la date), objet (bouton qui ouvre une popup qui propose de changer les champs de l'objet)-->
        <td v-for="cleChamp in Object.keys(champs)">
            <input class="form-control" type="text" v-model="champs[cleChamp]">
        </td>
        <td>
            <!-- TODO : aussi faire this.modeEdition=!this.modeEdition dans sauverScientifique -->
            <button class="btn btn-success" v-on:click="sauverScientifique()">Sauvegarder</button>
            <button class="btn btn-secondary" v-on:click="annuler()">Annuler</button>
        </td>
    </tr>
</template>