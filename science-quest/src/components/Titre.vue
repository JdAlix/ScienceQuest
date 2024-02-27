<script>
import { NOM_APP } from "../assets/const";

export default{
    data(){
        return{
            nomApp:NOM_APP

        }
    },
    methods:{
      changerDarkMode: function(){
        document.body.dataset.bsTheme!="dark" ? document.body.dataset.bsTheme="dark" : document.body.dataset.bsTheme="light"
      }
    }
}
</script>

<template>
        <!-- logo temporaire
        <img alt="Vue logo" src="../assets/logo.png" />
        -->
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#/">{{ nomApp }}</a>
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
          <a class="nav-link" href="#">Accueil</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Jeux
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="#">Qui est-ce</a></li>
            <li><a class="dropdown-item" href="#/kahoot">Kahoot</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item disabled" href="#">autre jeu</a></li>
          </ul>
        </li>
      </ul>
    </div>
    <button id="boutondarkmode" class="btn" v-on:click="changerDarkMode">💡</button>
    <!-- TODO : l'afficher que si on n'est pas connecté, sinon afficher l'username (qui sera surement dans le localstorage)-->
    <a class="nav-link" href="#/login">Se connecter</a>
  </div>
</nav>
</template>

<style scoped>

</style>