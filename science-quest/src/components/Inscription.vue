<script>
import { REST_API, ALGO_HASH_MDP } from '@/assets/const'

export default {
    methods:{
        creerCompte(event){
            event.stopPropagation()
            let donnees=Object.fromEntries(new FormData(formajouter))
            const donneesJson=JSON.stringify(donnees)
            console.log(donnees)
            fetch(REST_API+"/utilisateur", {method:"POST", body:donneesJson, headers:{"Content-Type":"application/json"}}).then(response=>console.log(response))
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