<script>
import { NOM_APP } from "../assets/const";

export default{
    data(){
        return{
            nomApp:NOM_APP

        }
    }
}

</script>

<template>
    <header>
        <!-- logo temporaire-->
        <img alt="Vue logo" src="../assets/logo.svg" />
        <h1>
            <a href="/">
                {{ nomApp }}
            </a>
        </h1>
        <div class="espacement">
            <!-- espacement pour mettre les boutons a droite-->
        </div>

        <!-- liste de trucs a cliquer; accueil, jouer, langues?-->
        <nav>
            <!-- todo : component pour faire type de bouton (normal, menu déroulant)-->
            <a>Accueil</a>
            <a>Jouer</a>
        </nav>
    </header>
</template>

<style scoped>
header{
    width:100%;
    height: 4em;
    background-color:gold;
    display:flex;
    flex:auto;
    flex-direction: row;
}

.espacement{
    flex:auto
}

h1{
    font-size: 2em;
}

nav{
    display:flex;
    flex:0;
    flex-direction: row;
}

nav a{
    background-color: aqua;
    vertical-align: center;
    padding:1em;
    height:100%;
}

</style>