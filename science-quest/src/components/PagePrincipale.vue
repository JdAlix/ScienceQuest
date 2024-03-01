<script>
import { NOM_APP } from "@/assets/const";

export default {
    data() {
        return {
            nomApp: NOM_APP
        }
    }
}
</script>

<style lang="scss">
@import "@/scss/landingPage.scss";
</style>

<template>
    <div class="landingPage">
        <div class="landingContent">
            <h1>{{ nomApp }}</h1>
            <p> Science Quest est un jeu réalisé par 6 étudiants en deuxième années de l'IUT Clermont Auvergne <br />
                dans le cadre d'un projet tuteuré. Le but de ce jeu est de vous faire découvrir des scientifiques (principalement des femmes) <br />
                et leurs découvertes à travers des énigmes et des mini-jeux. Bonne chance !
            </p>
            <p> Actuellement les jeux sont en cours de développement 🚧</p> <!-- TODO: Rajouter les jeux disponibles -->
            <div class="routes-button">
                <router-link to="/kahoot" class="btn btn-dark">Kahoot 🚧</router-link>
                <router-link to="/pendu" class="btn btn-dark">Pendu 🚧</router-link>
            </div>
            <img src="@/temporary_ressources/img/marie-curie.png" alt="Marie Curie" />
        </div>   
    </div>
    <footer>
        <p>Réalisé par des étudiants de l'IUT Clermont Auvergne <br /> <!-- TODO: Mettre les noms des étudiants -->
            © 2024 Science Quest
        </p> 
    </footer>
</template>
