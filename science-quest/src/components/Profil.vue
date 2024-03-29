<script>
import { RouterLink } from 'vue-router'
import { Utilisateur } from "@/data/utilisateur"

export default {
    data(){
        return {
            utilisateur:null
        }
    },
    mounted(){
        Utilisateur.utilisateurConnecte().then(user=>{
            this.utilisateur=user
            if(!this.utilisateur){
                //rediriger si on n'a pas d'utilisateur connecté
                //TODO : mettre noms aux routes au lieu de mettre le lien
                this.$router.push("/login")
            }
        })
    }
}

</script>


<template>
    {{ JSON.stringify(utilisateur) }}
</template>

<style>
form {
  display: flex;
  flex-direction:column;
  align-items: center;
}
</style>