<script>
export default{
    data(){
        return{
            nbLettresADeviner:0,
            progression:"",
            viesRestantes:0, //0 == pendu; partie terminée
            partieTerminee:false, //plus de lettres a deviner
            premierePartie:true, //ne pas afficher "Perdu" pour ceux qui viennent de rejoindre

            //local uniquement, le client ne saura pas le mot 
            debug_motADeviner:"einstein",
            debug_nbLettresADeviner:8,
            debug_lettresDejaDevine:"", //tout en minuscule*
            //bloquer l'input si l'utilisateur met une lettre deja devinée
            
        }
    },
    methods:{
        creerPartie: function(){
            this.partieTerminee=false
            //l'api (PATCH demarrerPartie) retournera le nombre de lettres a deviner ainsi que le nombre de vies
            this.nbLettresADeviner=this.debug_nbLettresADeviner; //TODO utiliser l'api
            this.viesRestantes=10 // TODO utiliser l'api

            this.progression="_".repeat(this.nbLettresADeviner);

            this.debug_lettresDejaDevine=""
        },
        deviner: function(event){
            //prendre la lettre depuis l'event
            const lettreDevinee=event.data
            //vider l'input
            event.target.value="";

            //envoyer lettreDevinee a l'api
            this.progression=this.debug_letreDevinee(lettreDevinee)
            if(!this.progression.includes("_")){
                //plus de lettres a deviner
                this.partieTerminee=true
            }
            this.viesRestantes-- //l'api pourrait nous donner le nombre de vies restantes
        },
        debug_letreDevinee: function(lettre){
            let progression=""
            this.debug_lettresDejaDevine+=lettre
            this.debug_motADeviner.split("").forEach(w=>this.debug_lettresDejaDevine.includes(w) ? progression+=w : progression+="_")
            return progression
        }
    }
}

</script>

<template>
    <div style="display:flex">
        <button v-on:click="creerPartie">aéaezaeaeazeaz</button>
        <!-- TODO : dessiner le pendu -->
        <div>
            <span>mot a deviner : </span>
            <h2 style="font-family: monospace">{{ progression }}</h2>
        </div>
        <input type="text" minlength="1" maxlength="1" @input="deviner" v-if="viesRestantes && !partieTerminee">
    </div>
</template>


<style>


</style>