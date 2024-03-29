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
        //rediriger si on n'a pas d'utilisateur connecté
        Utilisateur.utilisateurConnecte().then(user=>this.utilisateur=user)
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