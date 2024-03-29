<script>
import { Utilisateur } from "@/data/utilisateur"

export default {
    data(){
        return {
            messageErreur:""
        }
    },
    methods:{
        creerCompte(event){
            if(!formajouter.checkValidity()){
                return
            }
            const utilisateur=new Utilisateur(Object.fromEntries(new FormData(formajouter)))
            utilisateur.creerCompte().then(response=>this.$router.push("/login")).catch(ex=>this.messageErreur=ex)
        }
    }
    
}

</script>


<template>
    <form id="formajouter" @submit.prevent>
    <h1 class="h3 mb-3 fw-normal">S'inscrire</h1>

    <div class="form-floating">
        <input type="email" class="form-control" id="emailInput" name="email" required>
        <label for="emailInput">Email</label>
    </div>
    <div class="form-floating">
        <input type="text" class="form-control" id="pseudoInput" name="pseudo" required min="6">
        <label for="pseudoInput">Pseudo</label>
    </div>
    <div class="form-floating">
        <input type="password" class="form-control" id="motDePasseInput" name="motDePasse" required min="6">
        <label for="motDePasseInput">Mot de passe</label>
    </div>
    <br/>
    <p class="text-danger">{{ messageErreur }}</p>
    <br/>
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