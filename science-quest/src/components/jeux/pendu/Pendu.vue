<script>
export default{
    data(){
        return{
            nbLettresADeviner:0,
            progression:"",
            viesRestantes:0, //-1 == pendu; partie terminée, 
            partieTerminee:true, //plus de lettres a deviner
            premierePartie:true, //ne pas afficher "Perdu" pour ceux qui viennent de rejoindre
            lettresDejaDevine:"",

            //local uniquement, le client ne saura pas le mot 
            debug_motADeviner:"einstein",
            debug_nbLettresADeviner:8,
            debug_lettresDejaDevine:"", //tout en minuscule*
            //bloquer l'input si l'utilisateur met une lettre deja devinée
            
        }
    },
    methods:{
        creerPartie: function(){
            this.debug_creerPartie()
            this.premierePartie=false
            this.partieTerminee=false
            //l'api (PATCH demarrerPartie) retournera le nombre de lettres a deviner ainsi que le nombre de vies
            this.nbLettresADeviner=this.debug_nbLettresADeviner; //TODO utiliser l'api
            this.viesRestantes=10 // TODO utiliser l'api

            this.progression="_".repeat(this.nbLettresADeviner);

            this.lettresDejaDevine=""
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
            //ajouter la lettre dans la liste des lettres devinées
            if(!this.lettresDejaDevine.includes(lettreDevinee)){
                this.lettresDejaDevine+=lettreDevinee
            }
        },
        debug_letreDevinee: function(lettre){ //ce que l'api devrait faire
            if(this.viesRestantes<0){
                return this.debug_motADeviner //plus de vies = fin de la partie, l'api retourne le mot qu'on devait trouver
            }
            let progression=""
            this.debug_lettresDejaDevine+=lettre
            this.debug_motADeviner.split("").forEach(w=>this.debug_lettresDejaDevine.includes(w) ? progression+=w : progression+="_")
            return progression
        },
        debug_creerPartie: function(){
            this.debug_lettresDejaDevine=""
        }
    }
}

</script>

<template>
    <div>
        <div v-if="partieTerminee">
            <!-- hors partie -->
            <button v-on:click="creerPartie">Créer une partie</button>
            <div v-if="!premierePartie">
                <div v-if="viesRestantes>=0">
                    Gagné!
                </div>
                <div v-if="viesRestantes<0">
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
            <p>Mot a deviner ({{ nbLettresADeviner }} lettres) : </p>
            <h2 class="trous">{{ progression }}</h2>
            <input type="text" minlength="1" maxlength="1" @input="deviner" placeholder="Devinez la lettre ici">
            <p>Vies restantes : {{ viesRestantes }}</p>
            <p>Lettres devinées : <span style="font-family: monospace">{{ lettresDejaDevine }}</span></p>
        </div>
    </div>
</template>


<style>
.trous{
    letter-spacing:0.5em;
    font-family: monospace;
}

</style>