<script>
export default {
    props: ["champsInit"],
    data(){
        return{
            modeEdition:false, //vrai = remplacer les champs par des inputs

            champsAffiche:{nom:"Test", prenom:"Test"} //this.champsInit
        }
    },
    methods:{
        changerModeEdition: function(){
            this.modeEdition=!this.modeEdition
        },
        annuler: function(){
            //remettre les valeurs a 0
            this.champsAffiche=this.champsInit,

            this.changerModeEdition()
        },
        sauverScientifique: function(){
            //TODO : comme dans ajout

            //fetch("localhost/api/v1/scientifiques", {method:"PUT", body:JSON.stringify(donnees)})
            console.log(this.champsAffiche)
            this.changerModeEdition()
        }
    }
}
</script>


<template>
    <tr v-if="!this.modeEdition">
        <td v-for="item in champsAffiche">
            <p>{{ item }}</p>
        </td>
        <button class="btn-outline-secondary btn" v-on:click="changerModeEdition()">Modifier</button>
    </tr>
    <tr v-if="this.modeEdition">
        
        <td v-for="item in champsAffiche">
            <!--input class="form-control" type="text" v-model="item"-->
        </td>
        
        <!-- TODO : aussi faire this.modeEdition=!this.modeEdition dans sauverScientifique -->
        <button class="btn btn-success" v-on:click="sauverScientifique()">Sauvegarder</button>
        <button class="btn btn-secondary" v-on:click="annuler()">Annuler</button>
    </tr>
</template>