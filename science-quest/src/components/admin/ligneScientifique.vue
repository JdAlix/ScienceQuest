<script>
export default {
    props: ["id", "nom", "prenom", "descriptif","date"],
    data(){
        return{
            modeEdition:false, //vrai = remplacer les champs par des inputs

            nomAffiche:this.nom,
            prenomAffiche:this.prenom,
            descriptifAffiche:this.descriptif,
            dateAffiche:this.date
        }
    },
    methods:{
        changerModeEdition: function(){
            this.modeEdition=!this.modeEdition
        },
        annuler: function(){
            //remettre les valeurs a 0
            this.nomAffiche=this.nom,
            this.prenomAffiche=this.prenom,
            this.descriptifAffiche=this.descriptif,
            this.dateAffiche=this.date

            this.changerModeEdition()
        },
        sauverScientifique: function(){
            const donnees={
                id:this.id,
                nom:this.nomAffiche,
                prenom:this.prenomAffiche,
                descriptif:this.descriptifAffiche,
                date:this.dateAffiche,
            }

            fetch("localhost/api/v1/scientifiques", {method:"PUT", body:JSON.stringify(donnees)})
            this.changerModeEdition()
        }
    }
}
</script>


<template>
    <tr v-if="!this.modeEdition">
        <td>
            <p>{{ prenomAffiche }}</p>
        </td>
        <td>
            <p>{{ nomAffiche }}</p>
        </td>
        <td>
            <p>{{ descriptifAffiche }}</p>
        </td>
        <td>
            <p>{{ dateAffiche }}</p>
        </td>
        <button class="btn-outline-secondary btn" v-on:click="changerModeEdition()">Modifier</button>
    </tr>
    <tr v-if="this.modeEdition">
        
        <td>
            <input class="form-control" type="text" v-model="prenomAffiche">
        </td>
        <td>
            <input class="form-control" type="text" v-model="nomAffiche">
        </td>
        <td>
            <textarea class="form-control" v-model="descriptifAffiche"></textarea>
        </td>
        <td>
            <input class="form-control" type="date" v-model="dateAffiche">
        </td>
        
        <!-- TODO : aussi faire this.modeEdition=!this.modeEdition dans sauverScientifique -->
        <button class="btn btn-success" v-on:click="sauverScientifique()">Sauvegarder</button>
        <button class="btn btn-secondary" v-on:click="annuler()">Annuler</button>
    </tr>
</template>