<script>
import PenduDessin from './PenduDessin.vue'
import { REST_API } from "../../../assets/const";

export default{
    data() {
        return {
            nbLettresADeviner: 0,
            progression: "",
            viesRestantes: 0, //-1 == pendu; partie terminée, 
            partieTerminee: true, //plus de lettres a deviner
            premierePartie: true, //ne pas afficher "Perdu" pour ceux qui viennent de rejoindre
            lettresDejaDevine: "",
            
            //a recuperer a partir de l'api (prendre nom et prenom d'un scientifique nous meme) 
            motADeviner: "einstein",
            description: "", //s'affiche en dessous du resultat a la fin
            api_pagesMaximum: 0, //impossible de connaitre le nombre de page a l'avance

            regexExceptions: [ //caracteres qu'on ne fera pas deviner au joueur
                /\W/, //caracteres blanc
                /[^a-z]/, //non alphabetique minuscule
            ], 
        };
    },
    methods: {
        creerPartie: function () {
            this.lettresDejaDevine = "";
            //appeler l'API
            fetch(`${REST_API}/scientifiques?page=`+this.intAleatoire(this.api_pagesMaximum)).then(response=>{
                response.json().then(json=>{
                    //prendre le scientifique de la requete
                    const arrayScientifique=json._embedded
                    const scientifiqueADeviner=arrayScientifique[this.intAleatoire(arrayScientifique.length)]
                    //prendre le mot a deviner a partir du nom du scientifique
                    this.motADeviner = scientifiqueADeviner.nom.toLowerCase() + " " + scientifiqueADeviner.prenom.toLowerCase()
                    this.nbLettresADeviner = this.motADeviner.length
                    this.description = scientifiqueADeviner.descriptif

                    this.viesRestantes = 10; // TODO utiliser l'api

                    //verifier que le mot a deviner ne contient pas des lettres exemptées
                    this.lettresDejaDevine = "";
                    this.motADeviner.split("").forEach(lettre=>
                        this.regexExceptions.forEach(regex=>regex.test(lettre) ? this.lettresDejaDevine+=lettre /* faire jouer la lettre a la place de l'utilisateur */ : null)
                    )

                    //rafraichir la progression
                    this.progression = this.afficherProgression("")

                    //demarrer le jeu
                    this.afficherLeJeu()
                })
            })
        },
        afficherLeJeu(){
            this.partieTerminee = false;
            this.premierePartie = false;
        },
        deviner: function (event) {
            //TODO revoir ce truc
            //prendre la lettre depuis l'event
            const lettreDevinee = event.data.toLowerCase();
            //vider l'input
            event.target.value = "";
            //envoyer lettreDevinee a l'api
            const oldprogression = this.progression;
            this.progression = this.afficherProgression(lettreDevinee);

            // /!\ code temporaire, local uniquement : TODO remplacer avec l'api
            if (oldprogression == this.progression) {
                //si la lettre est incorrecte
                this.viesRestantes--; //l'api devrait aussi retourner le nombre de vies restantes
                if(this.viesRestantes<0){
                    this.partieTerminee = true
                    this.progression = this.afficherProgression(lettreDevinee);
                }
            }
            //fin code temporaire

            if (!this.progression.includes("_")) {
                //plus de lettres a deviner
                this.partieTerminee = true;
            }
            //ajouter la lettre dans la liste des lettres devinées
            if (!this.lettresDejaDevine.includes(lettreDevinee)) {
                this.lettresDejaDevine += lettreDevinee;
            }
        },
        afficherProgression: function (lettre) {
            if (this.viesRestantes < 0) {
                return this.motADeviner; //plus de vies = fin de la partie, l'api retourne le mot qu'on devait trouver
            }
            let progression = "";
            this.lettresDejaDevine += lettre;
            this.motADeviner.split("").forEach(w =>this.lettresDejaDevine.includes(w) ? progression += w : progression += "_");
            return progression;
        },
        intAleatoire: function(nbPages){
            return Math.floor(Math.random() * nbPages)
        }
    },
    components: { PenduDessin }
}

</script>

<template>
    <h1 style="padding-left: 0.5em;">Pendu</h1>
    <div class="separateur">
        <div v-if="partieTerminee" class="divjeu">
            <!-- hors partie -->
            <div v-if="!premierePartie">
                <div v-if="viesRestantes >= 0">
                    Gagné!
                </div>
                <div v-if="viesRestantes < 0">
                    Perdu!
                </div>
                <p>Le mot était : </p>
                <!-- l'api devrait retourner le mot entier quand la vie est a 0 -->
                <h2 style="font-family: monospace">{{ progression }}</h2>
                <p>{{ description }}</p>
            </div>
            <button class="btn btn-primary" v-on:click="creerPartie">Créer une partie</button>
        </div>

        <div v-if="!partieTerminee" class="divjeu">
            <!--PenduDessin></PenduDessin-->
        </div>
        
        <div v-if="!partieTerminee" class="divjeu">
            <!-- dans une partie -->
            
            <p>Mot a deviner ({{ nbLettresADeviner }} lettres) : </p>
            <h2 class="trous">{{ progression }}</h2>
            <input class="form-control" type="text" minlength="1" maxlength="1" @input="deviner"
                placeholder="Devinez la lettre ici">
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
.divjeu {
    text-align: center;
}

.separateur{
    display: flex;
    flex:1;
    justify-content: center;
}

</style>