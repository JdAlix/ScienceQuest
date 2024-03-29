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

            thematiquesDispo:[],
            choixThematiques:[],

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
        }
    },
	mounted(){
		ListeJeux.get().then(jeux=>this.jeuxDispo=Object.values(jeux))
        Difficultes.getPage(0,999).then(difficultes=>{
            this.difficultesDispo=difficultes._embedded
            //choisir une difficulté par défaut
            this.choixDifficulte=this.difficultesDispo[0].id
        })
        Thematiques.getPage(0,999).then(thematiques=>this.thematiquesDispo=thematiques._embedded)
	}
}

</script>


<template>
    <form @submit.prevent id="formajouter">
        <h1 class="h3 mb-3 fw-normal">Creer une partie</h1>

        <div class="form-floating">
        </div>
        <div class="form-floating">
            <select name="idJeu">
                <option v-for="jeu in jeuxDispo" :value="jeu.id" required>
                    {{ jeu.nom }}
                </option>
            </select>
        </div>
        <div class="checkbox mb-3">
            <label for="thematiquesInput">Thématiques</label>
            <br/>
            <select v-model="choixThematiques" id="thematiquesInput" multiple required>
                <option v-for="thematique in thematiquesDispo" :value="thematique.id">
                    {{ thematique.libelle }}
                </option>
            </select>
        </div>
        <div class="checkbox mb-3">
            <label for="idDifficulteInput">Difficulté</label>
            <br/>
            <select v-model="choixDifficulte" id="idDifficulteInput" name="idDifficulte" required>
                <option v-for="difficulte in difficultesDispo" :value="difficulte.id">
                    {{ difficulte.libelle }}
                </option>
            </select>
        </div>
        <button class="btn btn-lg btn-primary" @click="login">Créer une partie</button>
    </form>
</template>

<style>
form {
  display: flex;
  flex-direction:column;
  align-items: center;
}
</style>