<script>
import { REST_API } from '@/assets/const'
import { RouterLink } from 'vue-router'

export default {
    methods:{
        login: function (event){event.stopPropagation()
            let donnees=Object.fromEntries(new FormData(formajouter))

            const donneesJson=JSON.stringify(donnees)
            console.log(donnees)
            fetch(REST_API+"/utilisateur/connexion", {method:"POST", body:donneesJson, headers:{"Content-Type":"application/json"}}).then(response=>console.log(response))
        }
    }
}

</script>


<template>
    <form @submit.prevent>
    <h1 class="h3 mb-3 fw-normal">Se connecter</h1>

    <div class="form-floating">
        <input type="email" class="form-control" id="emailInput" name="email">
        <label for="emailInput">Email</label>
    </div>
    <div class="form-floating">
        <input type="password" class="form-control" id="motDePasseInput" name="motDePasse">
        <label for="motDePasseInput">Mot de passe</label>
    </div>

    <div class="checkbox mb-3">
        <label>
            <label for="rememberMe">Se souvenir de moi</label>
            <input type="checkbox" value="rememberMe" id="rememberMe" name="rememberMe">
        </label>
    </div>
    <RouterLink to="/inscription">Pas de compte?</RouterLink>
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