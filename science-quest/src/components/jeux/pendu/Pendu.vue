<script>
export default{
    data(){
        return{
            nbLettresADeviner:0,
            progression:"",
            viesRestantes:0, //0 == pendu; partie terminée, 
            partieTerminee:true, //plus de lettres a deviner
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
            this.premierePartie=false
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
            const oldprogression=this.progression;
            this.progression=this.debug_letreDevinee(lettreDevinee)

            // /!\ local uniquement : normalement c'est l'api qui gere les vies
            if(oldprogression==this.progression){
                //si la lettre est incorrecte
                this.viesRestantes-- //l'api devrait aussi retourner le nombre de vies restantes
            }
    

            if(!this.progression.includes("_")){
                //plus de lettres a deviner
                this.partieTerminee=true
            }
        },
        debug_letreDevinee: function(lettre){ //ce que l'api devrait faire
            if(this.viesRestantes<=0){
                return this.debug_motADeviner //plus de vies = fin de la partie, l'api retourne le mot qu'on devait trouver
            }
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
        <div v-if="partieTerminee">
            <!-- hors partie -->
            <button v-on:click="creerPartie">Créer une partie</button>
            <div v-if="!premierePartie">
                <div v-if="viesRestantes">
                    Gagné
                </div>
                <div v-if="viesRestantes<=0">
                    Perdu!
                </div>
                <p>Le mot était : </p>
                <!-- l'api devrait retourner le mot entier quand la vie est a 0 -->
                <h2 style="font-family: monospace">{{ progression }}</h2>
            </div>
        </div>
        <div v-if="!partieTerminee">
            <!-- dans une partie -->
            <!-- TODO : dessiner le pendu -->
            <p>mot a deviner : </p>
            <h2 style="font-family: monospace">{{ progression }}</h2>
            <input type="text" minlength="1" maxlength="1" @input="deviner" placeholder="Devinez la lettre ici">
        </div>
    </div>
</template>


<style>


</style>