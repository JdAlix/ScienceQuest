<script>
import { RouterLink } from 'vue-router'
import { Partie } from '@/data/partie';
import { ListeJeux } from '@/data/jeu';
import { Thematiques } from '@/data/thematique';
import { Difficultes } from '@/data/difficulte';

export default {
	data(){
		return {
			jeuxDispo:[],

			afficherChoixThematiques:false,
            thematiquesDispo:[],
            choixThematiques:[],

            afficherChoixDifficultes:false,
            difficultesDispo:[],
            choixDifficulte:-1,
		}
	},
    methods:{
        login(){
        const partieACreer=new Partie(Object.fromEntries(new FormData(formajouter)))
		partieACreer.thematiques=this.choixThematiques
		partieACreer.creerPartie().then(response=>console.log(response))
		//console.log(partieACreer)
		return
        utilisateur.login().then(response=>console.log(response))
        }
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
	mounted(){
		ListeJeux.get().then(jeux=>this.jeuxDispo=Object.values(jeux))
	}
}

</script>


<template>
    <form @submit.prevent id="formajouter">
    <h1 class="h3 mb-3 fw-normal">Creer une partie</h1>

    <div class="form-floating">
    </div>
    <div class="form-floating">
        <select>
            <option v-for="jeu in jeuxDispo" :value="jeu.id">
                {{ jeu.nom }}
            </option>
        </select>
    </div>
	<div class="checkbox mb-3">
                <label for="afficherChoixThematiquesCheckbox">Choisir une thématique </label>
                <input type="checkbox" id="afficherChoixThematiquesCheckbox" v-model="afficherChoixThematiques"/>
                <br/>
                <select v-if="afficherChoixThematiques" v-model="choixThematiques" multiple>
                    <option v-for="thematique in thematiquesDispo" :value="thematique.id">
                        {{ thematique.libelle }}
                    </option>
                </select>
            </div>
            <div class="checkbox mb-3">
                <label for="afficherChoixDifficultesCheckbox">Choisir une difficulté </label>
                <input type="checkbox" id="afficherChoixDifficultesCheckbox" v-model="afficherChoixDifficultes"/>
                <br/>
                <select v-if="afficherChoixDifficultes" v-model="choixDifficulte" name="idDifficulte">
                    <option v-for="difficulte in difficultesDispo" :value="difficulte.id">
                        {{ difficulte.libelle }}
                    </option>
                </select>
            </div>

    <div class="checkbox mb-3">
        <label>
            <label for="rememberMe">Se souvenir de moi</label>
            <input type="checkbox" value="1" id="rememberMe" name="rememberMe">
        </label>
    </div>
    <button class="btn btn-lg btn-primary" @click="login">Se connecter</button>
    </form>
</template>

<style>
form {
  display: flex;
  flex-direction:column;
  align-items: center;
}
</style>