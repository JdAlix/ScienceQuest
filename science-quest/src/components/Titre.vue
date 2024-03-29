<script>
import { NOM_APP } from "@/assets/const";
import { Utilisateur } from "@/data/utilisateur";

export default{
    data(){
        return{
            nomApp:NOM_APP,
            utilisateur:{},
            estConnecte:false,
        }
    },
    methods:{
      changerDarkMode: function(){
        document.body.dataset.bsTheme!="dark" ? document.body.dataset.bsTheme="dark" : document.body.dataset.bsTheme="light"
      }
    },
    mounted(){
      Utilisateur.utilisateurConnecte().then(user=>{
        this.estConnecte=user!=null
        this.utilisateur=user
      })
    }
}
</script>

<template>
        <!-- logo temporaire
        <img alt="Vue logo" src="@/assets/logo.png" />
        -->
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <router-link class="navbar-brand" to="/">{{ nomApp }}</router-link>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <!--li class="nav-item">
            TODO : trouver un moyen de gerer la page courante
          <a class="nav-link active" aria-current="page" href="#">Accueil</a>
        </li-->
        <li class="nav-item">
          <router-link class="nav-link" to="/">Accueil</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" to="/partie">Créer une partie</router-link>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Jeux
          </a>
          <ul class="dropdown-menu">
            <li><router-link class="dropdown-item" to="/pendu">Pendu</router-link></li>
            <li><router-link class="dropdown-item" to="/kahoot">Kahoot</router-link></li>
            <li><hr class="dropdown-divider"></li>
            <li><router-link class="dropdown-item disabled" to="/qui_est_ce">Qui-est-ce</router-link></li>
          </ul>
        </li>
      </ul>
    </div>
    <button id="boutondarkmode" class="btn" v-on:click="changerDarkMode">💡</button>
    <div v-if="!estConnecte">
      <router-link class="nav-link" to="/login">Se connecter</router-link>
    </div>
    <div v-if="estConnecte">
      <router-link class="nav-link" to="/profil">{{ utilisateur.pseudo }}</router-link>
    </div>
  </div>
</nav>
</template>

<style scoped>

</style>