<script>
import { REST_API } from '@/assets/const';

import LigneScientifique from './ligneScientifique.vue';

export default{
    data() {
        return {
            //données obtenues par l'api
            scientifiques: [],
            page:0,
            self:"",
            //HATEOAS
            last:null, // a prendre a partir de la requete
            prev:null,
            next:null,
            last:null,
        };
    },
    mounted(){
        //TODO faire route pour prendre la page a partir de l'URL
        this.self=`${REST_API}/scientifiques?page=${this.page}`
        this.getScientifiques(this.self)
    },
    methods:{
        getScientifiques(url){
            //HACK : s'assurer que les liens sont en HTTPS
            url=url.replace("http://", "https://")
            //enlever les anciens du tableau
            this.scientifiques.splice(0)
            //TODO : ajouter un delai si jamais la requete est trop rapide pour VueJS
            //appeler l'API
            fetch(url).then(response=>{
                response.json().then(json=>{
                    const oldLength=this.scientifiques.length
                    //prendre les scientifiques de la requete
                    this.scientifiques.push(...json._embedded)

                    //HATEOAS
                    this.self=json._links.self.href;
                    this.first=json._links.first ? json._links.first.href : null;
                    this.prev=json._links.prev ? json._links.prev.href : null;
                    this.next=json._links.next ? json._links.next.href : null;
                    this.last=json._links.last ? json._links.last.href : null;
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
    <button :class="{ invisible: !first }" class="btn btn-secondary" @click="this.getScientifiques(this.first)">First</button>
    <button :class="{ invisible: !prev }"  class="btn btn-secondary" @click="this.getScientifiques(this.prev)">Prev</button>
    <button :class="{ invisible: !next }"  class="btn btn-secondary" @click="this.getScientifiques(this.next)">Next</button>
    <button :class="{ invisible: !last }"  class="btn btn-secondary" @click="this.getScientifiques(this.last)">Last</button>
</template>