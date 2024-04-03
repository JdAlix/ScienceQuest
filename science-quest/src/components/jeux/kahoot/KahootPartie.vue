<script>
import { KAHOOT_NB_APRES_LA_VIRGULE_COMPTE_A_REBOURS} from "@/assets/const"
import { Kahoot } from "@/data/kahoot"

export default {
    data() {
        return {
			codePartie: this.$route.params.code ?? -1,
			kahootAPI:null,
			//unix timestamp pour indiquer la date limite pour repondre a la question, -1 indiquera la fin de la partie
			tempsLimite:0,
			compteARebours:0,
			compteAReboursId:0, //id donné par le setInterval pour pouvoir l'arreter quand il est a 0
			obtenirTimeoutId:0, //id donné par le setTimeout

			//affichage dans la vue
			etats:{
				question:true, //afficher la question
				score:false, //afficher les scores
				salleAttente:false, //afficher la salle d'attente (ecran avec pseudos)
			},

			//variables pour l'etat question
			question:{
				question:"",
				reponses:{},
			},
			//variables pour la salle d'attente
			salleAttente:{
				joueurs:[],
				partieDemarree:false,
			},
			//variables pour les scores
			score:{
				leaderboard:{},
				score:0,
				pointsGagne:0,
			},
			pointsAnimation:0
		}
	},
	mounted(){
		this.kahootAPI=new Kahoot(this.codePartie)
		this.obtenirSalleAttente()
		
	},
	unmounted(){
		//arreter le jeu quand la page n'est plus affichée
		window.clearTimeout(this.obtenirTimeoutId)
		window.clearInterval(this.compteAReboursId)
	},
	methods:{
		//retourne la fonction appropriée
		choisirLeProchainEtat(){
			//si la partie n'a pas démarrée, on reste dans la salle d'attente
			if(!this.salleAttente.partieDemarree){
				return this.obtenirSalleAttente
			}
			//si on vient de faire une question, on demander les resultats
			if(this.etats.question){
				return this.obtenirScores
			}
			return this.obtenirQuestion
		},
		obtenirQuestion(){
			this.resetEtats() //cacher l'etat precedent
			this.kahootAPI.obtenirQuestion().then(response=>{
				this.tempsLimite=response.tempsLimite
				//afficher cet etat
				this.etats.question=true

				this.question=response.questionActuel
				console.log(this.question)

				if(this.tempsLimite!=-1){
					//executer la fonction en boucle jusqu'a ce que la partie se termine
					this.obtenirTimeoutId=window.setTimeout(this.choisirLeProchainEtat(),(this.tempsLimite+100)-Date.now())
					//demarrer le compte a rebours
					this.compteAReboursId=window.setInterval(this.calculerCompteARebours,22)
				}
			}
			)
		},
		obtenirScores(){
			/*
			this.resetEtats() //cacher l'etat precedent
			this.kahootAPI.obtenirScore().then(response=>{
				this.tempsLimite=response.tempsLimite
				this.score=response
				//afficher cet etat
				this.etats.score=true

				//reduire les points pour pouvoir l'incrementer progressivement
				this.pointsAnimation=this.score.score-this.score.pointsGagne
				this.animerIncrementationPoints()

				if(this.tempsLimite!=-1){
					//executer la fonction en boucle jusqu'a ce que la partie se termine
					this.obtenirTimeoutId=window.setTimeout(this.choisirLeProchainEtat(),(this.tempsLimite+100)-Date.now())
					//demarrer le compte a rebours
					this.compteAReboursId=window.setInterval(this.calculerCompteARebours,22)
				}
			}
			)*/
		},
		obtenirSalleAttente(){
			this.resetEtats() //cacher l'etat precedent
			//afficher cet etat
			this.etats.salleAttente=true
			this.kahootAPI.obtenirSalleAttente().then(response=>{
				//TODO ENLEVER 
				this.kahootAPI.demarrerPartie().then()
				//FIN DEBUG
				this.tempsLimite=response.tempsLimite
				this.salleAttente=response

				this.obtenirTimeoutId=window.setTimeout(this.choisirLeProchainEtat(),(this.tempsLimite+100)-Date.now())
				if(this.salleAttente.partieDemarree){
					//demarrer le compte a rebours
					this.compteAReboursId=window.setInterval(this.calculerCompteARebours,22)
				}
			}
			)

		},
		repondre(reponse){
			this.kahootAPI.repondreQuestion(reponse).then()
			
			this.question.question=`Réponse "${reponse}" envoyée`
			this.question.reponses=[]
		},
		calculerCompteARebours(){
			if(this.tempsLimite<Date.now()){
				//si il reste plus de temps
				this.compteARebours=(0).toFixed(KAHOOT_NB_APRES_LA_VIRGULE_COMPTE_A_REBOURS)
				//arreter le compte a rebours
				window.clearInterval(this.compteAReboursId)
				return
			}
			this.compteARebours=((this.tempsLimite-Date.now())/1000).toFixed(KAHOOT_NB_APRES_LA_VIRGULE_COMPTE_A_REBOURS)
		},
		resetEtats(){
			Object.keys(this.etats).forEach(nomEtat=>this.etats[nomEtat]=0)
		},
		animerIncrementationPoints(){
			if(this.pointsAnimation<this.score.score){
				this.pointsAnimation++
				//continuer jusqu'a que les points sont tous additionnés
				window.setTimeout(this.animerIncrementationPoints,5)
			}
		},
	}
}

</script>

<style>
.jeu{
	display:flex;
	flex-direction: column;
	align-items: center;
	text-align: center;
}

.leaderboard{
	text-align: left;
}
</style>


<template>
<div class="jeu">
	<!-- Afficher le compte a rebours seulement quand la partie va demarrer, pour eviter de prendre par surprise les joueurs qui attendent dans la salle d'attente-->
	<p v-if="salleAttente.partieDemarree">Temps : {{ compteARebours }}s</p>
	<div v-show="etats.question">
		<p>{{ question.question }}</p>
		<button v-for="reponse in question.reponses" @click="repondre(reponse.id)">{{ reponse.reponse }}</button>
	</div>
	<div v-show="etats.score">
		<h2>Votre score : {{ pointsAnimation }} (+{{ score.pointsGagne }})</h2>
		<ol class="leaderboard">
			<li v-for="joueur in Object.keys(score.leaderboard)">
				{{ joueur }} : {{score.leaderboard[joueur]}}
			</li>
		</ol>
	</div>
	<div v-show="etats.salleAttente">
		<ul>
			<h2>Code : {{ codePartie }}</h2>
			<p>Invitez tout le monde !</p>
			<li v-for="joueur in salleAttente.joueurs">
				{{ joueur }}
			</li>
		</ul>
	</div>
</div>
</template>