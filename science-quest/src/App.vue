<script>
import PagePrincipale from './components/PagePrincipale.vue';
import Titre from './components/Titre.vue'
import NotFound from './components/erreurs/NotFound.vue';



const routes = {
  '/': PagePrincipale,
  '/about': PagePrincipale
}

export default {
    data() {
        return {
            currentPath: window.location.hash
        };
    },
    computed: {
        currentView() {
            return routes[this.currentPath.slice(1) || '/'] || NotFound;
        }
    },
    mounted() {
        window.addEventListener('hashchange', () => {
            this.currentPath = window.location.hash;
        });
    },
    components: { Titre }
}
</script>

<template>
  <!-- header -->
  <Titre />

  <main>
    <!-- todo -->
    <component :is="currentView" />
  </main>
</template>

<style scoped>

</style>
