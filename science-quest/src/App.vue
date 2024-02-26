<script>
import Titre from './components/Titre.vue'

export default {
    components: { Titre }
}
</script>

<template>
  <!-- header -->
  <Titre />

  <main>
    <!-- todo -->
    <router-view></router-view>
  </main>
</template>

<style scoped>

</style>
