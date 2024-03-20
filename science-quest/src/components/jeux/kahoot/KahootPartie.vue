<script>

export default {
    data() {
        return {
			codePartie: this.$route.params.code ?? -1,
			//unix timestamp pour indiquer la date limite pour repondre a la question, -1 indiquera la fin de la partie
			tempsLimite:0,
			compteARebours:0,
			compteAReboursId:0, //id donné par le setInterval pour pouvoir l'arreter quand il est a 0
			obtenirTimeoutId:0, //id donné par le setTimeout

			modes:{
				question:true, //afficher la question
				score:false, //afficher les scores
				salleAttente:false, //afficher la salle d'attente (ecran avec pseudos)
			},

			//variables pour mode question
			question:"",
			reponses:[],
			//variables pour la salle d'attente
			joueurs:[],
			partieDemarree:false,
			//variables pour les scores
			leaderboard:{},
			score:0,
			pointsGagne:0,

			DEBUG_temps:1000,
		}
	},
	mounted(){
		this.obtenirSalleAttente()
	},
	unmounted(){
		//arreter le jeu quand la page n'est plus affichée
		window.clearTimeout(this.obtenirTimeoutId)
		window.clearInterval(this.compteAReboursId)
	},
	methods:{
		obtenirQuestion(){
			this.resetModes() //cacher le mode precedent
			this.DEBUG_obtenirQuestion().then(response=>{
				//afficher ce mode
				this.modes.question=true

				this.question=response.question
				this.reponses=response.reponses
				this.tempsLimite=response.tempsLimite
				if(this.tempsLimite!=-1){
					//executer la fonction en boucle jusqu'a ce que la partie se termine
					this.obtenirTimeoutId=window.setTimeout(this.obtenirScores,(this.tempsLimite+100)-Date.now())
					//demarrer le compte a rebours
					this.compteAReboursId=window.setInterval(this.calculerCompteARebours,22)
				}
			}
			)
		},
		obtenirScores(){
			this.resetModes() //cacher le mode precedent
			this.DEBUG_obtenirScore().then(response=>{
				//afficher ce mode
				this.modes.score=true

				this.leaderboard=response.leaderboard
				this.score=response.score
				this.pointsGagne=response.pointsGagne
				this.tempsLimite=response.tempsLimite
				if(this.tempsLimite!=-1){
					//executer la fonction en boucle jusqu'a ce que la partie se termine
					this.obtenirTimeoutId=window.setTimeout(this.obtenirQuestion,(this.tempsLimite+100)-Date.now())
					//demarrer le compte a rebours
					this.compteAReboursId=window.setInterval(this.calculerCompteARebours,22)
				}
			}
			)
		},
		obtenirSalleAttente(){
			this.resetModes() //cacher le mode precedent
			//afficher ce mode
			this.modes.salleAttente=true
			this.DEBUG_obtenirSalleAttente().then(response=>{
				this.partieDemarree=response.partieDemarree
				this.joueurs=response.joueurs
				this.tempsLimite=response.tempsLimite
				if(this.partieDemarree){
					this.obtenirTimeoutId=window.setTimeout(this.obtenirQuestion,(this.tempsLimite+100)-Date.now())
					//demarrer le compte a rebours
					this.compteAReboursId=window.setInterval(this.calculerCompteARebours,22)
				} else {
					this.obtenirTimeoutId=window.setTimeout(this.obtenirSalleAttente,(this.tempsLimite+100)-Date.now())
				}
			}
			)

		},
		repondre(reponse){
			this.question=`Réponse "${reponse}" envoyée`
			this.reponses=[]
		},
		calculerCompteARebours(){
			if(this.tempsLimite<Date.now()){
				//si il reste plus de temps
				this.compteARebours=0
				//arreter le compte a rebours
				window.clearInterval(this.compteAReboursId)
				return
			}
			this.compteARebours=((this.tempsLimite-Date.now())/1000).toFixed(2)
		},
		
		resetModes(){
			Object.keys(this.modes).forEach(nomMode=>this.modes[nomMode]=0)
		},
		//simuler l'api
		async DEBUG_obtenirQuestion(){
			return JSON.parse(`
				{
					"question":"Qui a reçu le prix Nobel de chimie en 1911, pour avoir réussi à isoler un gramme de radium ?",
					"reponses":["Marie Curie","Einstein","Sophie Germain","Ada Lovelace"],
					"tempsLimite":${Date.now()+this.DEBUG_temps /* maintenant + 10 secondes pour repondre*/}
				}
			`)
		},
		async DEBUG_obtenirScore(){
			return JSON.parse(`
				{
					"score":1337,
					"pointsGagne":100,
					"leaderboard":{"Moi":1337, "Titouan":320},
					"tempsLimite":${Date.now()+this.DEBUG_temps /* maintenant + 10 secondes le temps de regarder les scores*/}
				}
			`)
		},
		async DEBUG_obtenirSalleAttente(){
			return JSON.parse(`
				{
					"joueurs":["Moi","Titouan"],
					"partieDemarree":true,
					"tempsLimite":${Date.now()+this.DEBUG_temps /* maintenant + 1 seconde*/}
				}
			`)
		},
	}
}

</script>


<template>
	
	<p>debug : code partie {{ codePartie }}</p>
	<div v-show="modes.question">
		<p>Temps : {{ compteARebours }}s</p>
		<p>{{ question }}</p>
		<button v-for="reponse in reponses" @click="repondre(reponse)">{{ reponse }}</button>
	</div>
	<div v-show="modes.score">
		<p>Temps : {{ compteARebours }}s</p>
		<h2>Votre score : {{ score }} (+{{ pointsGagne }})</h2>
		<ol>
			<li v-for="joueur in Object.keys(leaderboard)">
				{{ joueur }} : {{leaderboard[joueur]}}
			</li>
		</ol>
	</div>
	<div v-show="modes.salleAttente">
		<!-- Afficher le compte a rebours seulement quand la partie va demarrer, pour eviter de prendre par surprise les joueurs-->
		<p v-if="partieDemarree">Temps : {{ compteARebours }}s</p>
		<ul>
			<li v-for="joueur in joueurs">
				{{ joueur }}
			</li>
		</ul>
	</div>
</template>