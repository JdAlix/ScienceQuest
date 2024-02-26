<script>
export default {
    data() {
        return {
            email:"",
            password:""
        }
    },
    methods:{
        login: function (event){
            event.stopPropagation()
            //TODO : se connecter avec l'api et stocker l'id de session que renvoie l'api dans un cookie
            console.log("yay")
             //this.email et this.password synchronisés avec v-model
            console.log(this.email)
            console.log(this.password)
            
        }
    }
}

</script>


<template>
    <form  @submit.prevent>
    <h1 class="h3 mb-3 fw-normal">Se connecter</h1>

    <div class="form-floating">
        <input v-model="email" type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
        <label for="floatingInput">Email</label>
    </div>
    <div class="form-floating">
        <input v-model="password" type="password" class="form-control" id="floatingPassword" placeholder="Password">
        <label for="floatingPassword">Mot de passe</label>
    </div>

    <div class="checkbox mb-3">
        <label>
            <input type="checkbox" value="remember-me"> Se souvenir de moi
        </label>
    </div>
    <button class="btn btn-lg btn-primary" v-on:click="login">Se connecter</button>
    </form>
</template>

<style>
form {
  display: flex;
  flex-direction:column;
  align-items: center;
}
</style>