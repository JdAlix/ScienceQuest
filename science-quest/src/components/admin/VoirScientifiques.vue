<script>
import { REST_API } from '@/assets/const';

import LigneScientifique from './ligneScientifique.vue';

export default{
    data() {
        return {
            //données obtenues par l'api
            scientifiques: [],
            page:0,
        };
    },
    mounted(){
        //TODO faire route pour prendre la page a partir de l'URL
        this.getScientifiques(this.page)
    },
    methods:{
        getScientifiques(page){
            //enlever les anciens du tableau
            this.scientifiques.splice(0)
            //TODO : ajouter un delai si jamais la requete est trop rapide pour VueJS
            //appeler l'API
            fetch(`${REST_API}/scientifiques?page=${page}`).then(response=>{
                response.json().then(json=>{
                    const oldLength=this.scientifiques.length
                    //prendre le scientifique de la requete
                    this.scientifiques.push(...json._embedded)
                })
            })
        }
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
    :date="scientifique.dateNaissance"
    :descriptif="scientifique.descriptif"
    ></LigneScientifique>
</tbody>
    </table>
    <button @click="this.getScientifiques(++this.page)">Next</button>
    <!-- TODO : pagination -->
</template>