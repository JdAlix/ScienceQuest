<script>
import KahootListeParties from './KahootListeParties.vue'

 //TODO définir les méthodes -> à définir grâce à l'API
export default {
    data() {
        return {
            //creer partie
            titreKahoot: "",
            nbQuestions: 0,
            //rejoindre partie
            codeKahoot: "",
            //listes parties crees ( TODO : appeler l'api pour obtenir les parties)
            partiesCrees: [
            {titreKahoot:"Titre du Kahoot", nbQuestions:10, createur:"Professeur X"},
            {titreKahoot:"Titre du Kahoot2", nbQuestions:69, createur:"Professeur Y"},
            {titreKahoot:"Titre du Kahoot3", nbQuestions:234, createur:"Professeur XXX"},
                
            ]
        };
    },
    methods: {
        // TODO : demander a l'api de creer un kahoot (et rediriger vers la partie si possible via HATEOAS)
        creerKahoot: function () {
            console.log("yay");
            //this.titreKahoot et this.nbQuestions synchronisés avec v-model
            console.log(this.titreKahoot);
            console.log(this.nbQuestions);
        },
        // TODO : demander a l'api de rejoindre un kahoot (et rediriger vers la partie si possible via HATEOAS)
        rejoindrePartie: function () {
            console.log("yay");
            //this.titreKahoot et this.nbQuestions synchronisés avec v-model
            console.log(this.codeKahoot);
        },
    },
    components: { KahootListeParties }
}
</script>

<style lang="scss">
@import "../../../scss/kahoot.scss";
</style>

<template>
    <div class="Kahoot">
        <h1 style="padding-left: 0.5em;">Kahoot</h1>
        <div class="Kahoot-Header">
            <h2>Rejoindre un Kahoot</h2>
            <button type="button" class="btn btn-light" data-bs-toggle="modal" data-bs-target="#createKahootModal">
                Créer un Kahoot
            </button>
            <form @submit.prevent>
                <label for="Kahoot-Code">Code</label>
                <input type="text" id="Kahoot-Code" name="Kahoot-Code" v-model="codeKahoot" minlength="6" maxlength="10">
                <button class="btn btn-light" v-on:click="rejoindrePartie">Rejoindre</button>
            </form>
        </div>
        <div class="Kahoot-content">
            <div class="Kahoot-List">
                <h2> Vos Quizz</h2>
                <KahootListeParties v-for="partie in partiesCrees" :titreKahoot="partie.titreKahoot" :nbQuestions="partie.nbQuestions" :createur="partie.createur"></KahootListeParties>
            </div>
        </div>
    </div>
    <div class="modal fade" id="createKahootModal" tabindex="-1" role="dialog" aria-labelledby="creationKahoot" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h2 class="modal-title" id="creationKahoot">Créer un Kahoot</h2> <!-- TODO : Il faut changer le formulaire pour juste cliquer sur un bouton et que ça affiche une pop-up -->
                </div>
                <form @submit.prevent>
                <div class="modal-body">
                    <div>
                    <label for="Kahoot-Create-Title">Titre</label>
                    <input type="text" id="Kahoot-Create-Title" name="Kahoot-Create-Title" v-model="titreKahoot" required minlength="2" maxlength="255">
                    </div>
                    <div>
                    <label for="Kahoot-Create-Questions">Nombre de questions</label>
                    <input type="number" id="Kahoot-Create-Questions" name="Kahoot-Create-Questions" v-model="nbQuestions" required min="1" max="99">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                    <button class="btn btn-primary" v-on:click="creerKahoot">Créer</button>
                </div>
                </form>
            </div>
        </div>
    </div>
</template>
