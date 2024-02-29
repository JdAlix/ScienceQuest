<script>
import { REST_API } from '@/assets/const';

import LigneScientifique from './ligneScientifique.vue';

export default{
    data() {
        return {
            //données obtenues par l'api
            scientifiques: [
            ],
            page:0,
        };
    },
    mounted(){
        this.getScientifiques(0)
    },
    methods:{
        getScientifiques(page){
            //appeler l'API
            fetch(`${REST_API}/scientifiques?page=${page}`).then(response=>{
                response.json().then(json=>{
                    //prendre le scientifique de la requete
                    this.scientifiques=json._embedded

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
    <!-- TODO : pagination -->
</template>