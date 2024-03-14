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
            //TODO comment trouver l'endpoint depuis Liste.vue ?
            //fetch("localhost/api/v1/scientifiques", {method:"PUT", body:JSON.stringify(donnees)})
            console.log(this.champs)
            this.changerModeEdition()
        },
        typeDeChamp: function(champ){
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
        estUneDate: function(date) {
            return new Date(date) != "Invalid Date";
        }
    }
}
</script>


<template>
    <tr v-if="!this.modeEdition">
        <td v-for="champ in champs">
            <p>{{ this.typeDeChamp(champ)=="date" ? new Date(champ).toLocaleString() : champ }}</p>
        </td>
        <td>
            <button class="btn-outline-secondary btn" v-on:click="changerModeEdition()">Modifier</button>
        </td>
    </tr>
    <tr v-if="this.modeEdition">
        
        <!-- TODO : fix date, creer input objet (bouton qui ouvre une popup qui propose de changer les champs de l'objet)-->
        <td v-for="cleChamp in Object.keys(champs)">
            <input class="form-control" :type="typeDeChamp(champs[cleChamp])" v-model="champs[cleChamp]" :aria-label="cleChamp">
        </td>
        <td>
            <button class="btn btn-success" v-on:click="sauverScientifique()">Sauvegarder</button>
            <button class="btn btn-secondary" v-on:click="annuler()">Annuler</button>
        </td>
    </tr>
</template>