<script>

export default {
    data() {
        return {
			codePartie: this.$route.params.code ?? -1,
			//unix timestamp pour indiquer la date limite pour repondre a la question, -1 indiquera la fin de la partie
			dateFinDeLaQuestion:0,

			//note au back : l'API devra renvoyer les scores comme si c'etait une question avec une seule (ou 0) réponse
			question:"",
			//0-1 reponse = notification (bouton OK, par ex: afficher les scores, leaderboard etc...), 2-4 réponses = réponses a la question
			reponses:[],

			//DEBUG/STUB variables temporaire pour remplacer l'API
			DEBUG_obtenirQuestion:null
		}
	},
	mounted(){
		this.obtenirQuestion()
	},
	methods:{
		obtenirQuestion(){
			// DEBUG : alterner entre question et score
			if(this.DEBUG_obtenirQuestion==this.DEBUG_obtenirQuestionNormale){
				this.DEBUG_obtenirQuestion=this.DEBUG_obtenirQuestionScore
			} else {
				this.DEBUG_obtenirQuestion=this.DEBUG_obtenirQuestionNormale
			}
			this.DEBUG_obtenirQuestion().then(response=>{
				this.question=response.question
				this.reponses=response.reponses
				this.dateFinDeLaQuestion=response.tempsLimite
				if(this.dateFinDeLaQuestion!=-1){
					//executer la fonction en boucle jusqu'a ce que la partie se termine
					window.setTimeout(this.obtenirQuestion,(this.dateFinDeLaQuestion+100)-Date.now())
				}
			}
			)
		},
		//simuler l'api
		async DEBUG_obtenirQuestionNormale(){
			return JSON.parse(`
				{
					"question":"Qui a reçu le prix Nobel de chimie en 1911, pour avoir réussi à isoler un gramme de radium ?${Date.now()}",
					"reponses":["Marie-Curie","Einstein","Sophie Germain","Ada Lovelace"],
					"tempsLimite":${Date.now()+10000 /* maintenant + 10 secondes pour repondre*/}
				}
			`)
		},
		async DEBUG_obtenirQuestionScore(){
			return JSON.parse(`
				{
					"question":"Score : 1337, *Top 1* Moi : 1337, *Top 2* Titouan : 320",
					"reponses":[],
					"tempsLimite":${Date.now()+10000 /* maintenant + 10 secondes le temps de regarder les scores*/}
				}
			`)
		}
	}
}

</script>


<template>
	<p>debug : code partie {{ codePartie }}</p>
	<p>{{ question }}</p>
	<p>{{  Date.now() }}</p>
</template>