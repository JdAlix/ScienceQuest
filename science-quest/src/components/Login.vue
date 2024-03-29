<script>
import { RouterLink } from 'vue-router'
import { Utilisateur } from "@/data/utilisateur"

export default {
    methods:{
        login(){
            if(!formajouter.checkValidity()){
                return
            }
            const utilisateur=new Utilisateur(Object.fromEntries(new FormData(formajouter)))
            utilisateur.connecter().then(response=>console.log(response))
        }
    }
}

</script>


<template>
    <form @submit.prevent id="formajouter">
    <h1 class="h3 mb-3 fw-normal">Se connecter</h1>

    <div class="form-floating">
        <input type="email" class="form-control" id="emailInput" name="email" required>
        <label for="emailInput">Email</label>
    </div>
    <div class="form-floating">
        <input type="password" class="form-control" id="motDePasseInput" name="motDePasse" required min="6">
        <label for="motDePasseInput">Mot de passe</label>
    </div>

    <!--div class="checkbox mb-3">
        <label>
            <label for="rememberMe">Se souvenir de moi</label>
            <input type="checkbox" value="1" id="rememberMe" name="rememberMe">
        </label>
    </div-->
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