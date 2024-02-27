<script>
import LigneScientifique from './ligneScientifique.vue';

export default{
    data() {
        return {
            //données obtenues par l'api
            scientifiques: [
            {prenom:"marie",nom:"curie",date:"1900-01-13",descriptif:"radaition qqch comme ca"},
            {prenom:"albert",nom:"einstein",date:"1900-01-13",descriptif:"theorie"}
            ]
        };
    },
    components: { LigneScientifique }
}
</script>


<template>
    <table class="table">
        <thead>
    <tr>
        <th scope="col">Prenom</th>
        <th scope="col">Nom</th>
        <th scope="col">Descriptif</th>
        <th scope="col">Date de naissance</th>
        <th scope="col">Action</th>
    </tr>
  </thead>
  <tbody>
    <LigneScientifique v-for="scientifique in scientifiques"
    :prenom="scientifique.prenom"
    :nom="scientifique.nom"
    :date="scientifique.date"
    :descriptif="scientifique.descriptif"
    ></LigneScientifique>
</tbody>
    </table>
</template>