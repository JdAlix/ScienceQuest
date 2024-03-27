<script>
import PenduDessin from './PenduDessin.vue'
import { REST_API } from "@/assets/const";
import { Scientifiques } from "@/data/scientifique"
import { Thematiques } from '@/data/thematique';
import { Difficultes } from '@/data/difficulte';

export default{
    data() {
        return {
            nbLettresADeviner: 0,
            progression: "",
            viesRestantes: 0, //-1 == pendu; partie terminée, 
            viesMaximum:10,
            partieTerminee: true, //plus de lettres a deviner
            premierePartie: true, //ne pas afficher "Perdu" pour ceux qui viennent de rejoindre
            lettresDejaDevine: "",

            afficherChoixThematiques:false,
            thematiquesDispo:[],
            choixThematique:-1,

            afficherChoixDifficultes:false,
            difficultesDispo:[],
            choixDifficulte:-1,
            
            //a recuperer a partir de l'api (prendre nom et prenom d'un scientifique nous meme) 
            motADeviner: "einstein",
            description: "", //s'affiche en dessous du resultat a la fin
            imageScientifique: "",
            api_pagesMaximum: 0, //impossible de connaitre le nombre de page a l'avance

            regexExceptions: [ //caracteres qu'on ne fera pas deviner au joueur
                /\W/, //caracteres blanc
                /[^a-z]/, //non alphabetique minuscule
            ],
            lettresANePasFaireDevinerAuJoueur:"", //meme utilité que lettresDejaDevine mais n'est pas visible au joueur
        };
    },
    watch:{
        afficherChoixThematiques(to){
            if(to && this.thematiquesDispo.length==0){
                Thematiques.getPage(0,999).then(thematiques=>this.thematiquesDispo=thematiques._embedded)
            }
        },
        afficherChoixDifficultes(to){
            if(to && this.difficultesDispo.length==0){
                Difficultes.getPage(0,999).then(difficultes=>this.difficultesDispo=difficultes._embedded)
            }
        }
    },
    methods: {
        creerPartie: function () {
            this.lettresDejaDevine = "";
            this.lettresANePasFaireDevinerAuJoueur="";
            this.progression="";

            //appeler l'API
            Scientifiques.getPage(
                this.intAleatoire(this.api_pagesMaximum),
                0,
                this.afficherChoixThematiques ? this.choixThematique : -1,
                this.afficherChoixDifficultes ? this.choixDifficulte : -1
                ).then(json=>{
                    //prendre le scientifique de la requete
                    const arrayScientifique=json._embedded
                    const scientifiqueADeviner=arrayScientifique[this.intAleatoire(arrayScientifique.length)]
                    //prendre le mot a deviner a partir du nom du scientifique
                    this.motADeviner = scientifiqueADeviner.nomComplet.toLowerCase()
                    this.description = scientifiqueADeviner.descriptif
                    this.imageScientifique = scientifiqueADeviner.pathToPhoto

                    //mettre a jour le nombre de pages maximum de l'api scientifiques
                    this.api_pagesMaximum=json.page.totalPages

                    //verifier que le mot a deviner ne contient pas des lettres exemptées
                    this.motADeviner.split("").forEach(lettre=>
                        this.regexExceptions.forEach(regex=>regex.test(lettre) ? this.lettresANePasFaireDevinerAuJoueur+=lettre /* faire jouer la lettre a la place de l'utilisateur */ : null)
                    )

                    //rafraichir la progression pour enlever les lettres a ne pas faire deviner
                    this.progression = this.afficherProgression()
                    //compter le nombre de trous (enlever tout ce qui est pas underscore et compter)
                    this.nbLettresADeviner = this.progression.replace(/[^_]/g, "").length

                    this.viesRestantes=this.viesMaximum;
                    //demarrer le jeu
                    this.afficherLeJeu()
            })
        },
        afficherLeJeu(){
            this.partieTerminee = false;
            this.premierePartie = false;
        },
        deviner: function (event) {
            //prendre la lettre depuis l'event
            const lettreDevinee = event.data.toLowerCase();
            //vider l'input
            event.target.value = "";
            //voir si la lettre devinée est valide
            let lettreValide=true
            this.regexExceptions.forEach(regex=>lettreValide ? lettreValide=!regex.test(lettreDevinee) : null)
            if(!lettreValide){
                //ne pas faire deviner une lettre invalide
                return
            }
            //ajouter la lettre dans la liste des lettres devinées
            if (!this.lettresDejaDevine.includes(lettreDevinee)) {
                this.lettresDejaDevine += lettreDevinee;
            } else {
                //ne pas faire deviner une lettre qui a deja été devinée
                return
            }

            //comparer la progression
            const oldprogression = this.progression;
            this.progression = this.afficherProgression();

            if (oldprogression == this.progression) {
                //si on n'a pas progressé = lettre incorrecte
                this.viesRestantes--; //l'api devrait aussi retourner le nombre de vies restantes

                if(this.viesRestantes<0){
                    this.partieTerminee = true
                    this.progression = this.afficherProgression();
                }
            } else if (!this.progression.includes("_")) {
                    //plus de lettres a deviner
                    this.partieTerminee = true;
            }
        },
        afficherProgression: function () {
            if (this.viesRestantes < 0) {
                return this.motADeviner; //plus de vies = fin de la partie, on retourne le mot qu'on devait trouver
            }
            let progression = "";
            const lettresAAfficher=this.lettresDejaDevine + this.lettresANePasFaireDevinerAuJoueur;
            this.motADeviner.split("").forEach(w =>lettresAAfficher.includes(w) ? progression += w : progression += "_");
            return progression;
        },
        intAleatoire: function(nb){
            return Math.floor(Math.random() * nb)
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
                <img :src="imageScientifique">
            </div>
            <button class="btn btn-primary" v-on:click="creerPartie">Créer une partie</button>
            <div>
                <label for="afficherChoixThematiquesCheckbox">Choisir une thématique </label>
                <input type="checkbox" id="afficherChoixThematiquesCheckbox" v-model="afficherChoixThematiques"/>
                <br/>
                <select v-if="afficherChoixThematiques" v-model="choixThematique">
                    <option v-for="thematique in thematiquesDispo" :value="thematique.id">
                        {{ thematique.libelle }}
                    </option>
                </select>
            </div>
            <div>
                <label for="afficherChoixDifficultesCheckbox">Choisir une difficulté </label>
                <input type="checkbox" id="afficherChoixDifficultesCheckbox" v-model="afficherChoixDifficultes"/>
                <br/>
                <select v-if="afficherChoixDifficultes" v-model="choixDifficulte">
                    <option v-for="difficulte in difficultesDispo" :value="difficulte.id">
                        {{ difficulte.libelle }}
                    </option>
                </select>
            </div>
        </div>

        <div v-if="!partieTerminee" class="divjeu">
            <!--PenduDessin :viesRestantes="viesRestantes"></PenduDessin-->
        </div>
        
        <div v-if="!partieTerminee" class="divjeu">
            <!-- dans une partie -->
            
            <p>Mot a deviner ({{ nbLettresADeviner }} lettres) : </p>
            <h2 class="trous">{{ progression }}</h2>
            <input class="form-control" type="text" minlength="1" maxlength="1" @input="deviner"
                placeholder="Devinez la lettre ici">
            <p>Vies restantes : {{ viesRestantes }}</p>
            <label for="barreViePendu">Barre de vie</label>
            <meter min="0" :max="viesMaximum" :value="viesRestantes"></meter>
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