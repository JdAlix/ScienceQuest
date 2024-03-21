<script>
import { REST_API } from '@/assets/const'

//TODO : merger login et inscription ?

export default {
    methods:{
        creerCompte(event){
            event.stopPropagation()
            const donnees=new FormData(formajouter)
            //TODO : hasher le mdp
            const donneesJson=JSON.stringify(Object.fromEntries(donnees))

            console.log(donnees)
            //fetch(REST_API+"/utilisateur", {method:"POST", body:donneesJson}).then(response=>)
        }
    }
}

</script>


<template>
    <form id="formajouter" @submit.prevent>
    <h1 class="h3 mb-3 fw-normal">S'inscrire</h1>

    <div class="form-floating">
        <input type="email" class="form-control" id="emailInput" name="email">
        <label for="emailInput">Email</label>
    </div>
    <div class="form-floating">
        <input type="text" class="form-control" id="pseudoInput" name="pseudo">
        <label for="pseudoInput">Pseudo</label>
    </div>
    <div class="form-floating">
        <input type="password" class="form-control" id="motDePasseInput" name="motDePasse">
        <label for="motDePasseInput">Mot de passe</label>
    </div>
    <button class="btn btn-lg btn-primary" @click="creerCompte">S'inscrire</button>
    </form>
</template>

<style>
form {
  display: flex;
  flex-direction:column;
  align-items: center;
}
</style>