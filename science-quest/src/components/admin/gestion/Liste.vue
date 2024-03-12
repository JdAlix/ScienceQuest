<script>
import { REST_API } from '@/assets/const';

import LigneScientifique from './ligne.vue';

export default{
    data() {
        return {
            endpoint:"thematiques", //endpoint de l'api a recuperer
            //données obtenues par l'api
            scientifiques: [],
            page:0,

            //HATEOAS
            self:"",
            first:null, // a prendre a partir de la requete
            prev:null,
            next:null,
            last:null,
        };
    },
    mounted(){
        //TODO faire route pour prendre la page a partir de l'URL
        this.self=`${REST_API}/${this.endpoint}?page=${this.page}`
        this.getScientifiques(this.self)
    },
    methods:{
        getScientifiques(url){
            //HACK : s'assurer que les liens sont en HTTPS
            url=url.replace("http://", "https://")
            //enlever les anciens du tableau
            this.scientifiques=[]
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
    <!-- TODO : remplacer input par select ?-->
    <label for="endpoint">Endpoint API (REST) {{ REST_API }}</label>
    <input v-model="endpoint" id="endpoint">
    <button>Rafraichir</button>
    <table class="table">
        <thead>
    <tr>
        <th scope="col" v-for="nomColonne in Object.keys(scientifiques[0]??{})">{{nomColonne}}</th>
    </tr>
  </thead>
  <tbody>
    <LigneScientifique v-for="champ in scientifiques"
    :champs-init="champ"
    ></LigneScientifique>
</tbody>
    </table>
    <button :class="{ invisible: !first }" class="btn btn-secondary" @click="this.getScientifiques(this.first)">First</button>
    <button :class="{ invisible: !prev }"  class="btn btn-secondary" @click="this.getScientifiques(this.prev)">Prev</button>
    <button :class="{ invisible: !next }"  class="btn btn-secondary" @click="this.getScientifiques(this.next)">Next</button>
    <button :class="{ invisible: !last }"  class="btn btn-secondary" @click="this.getScientifiques(this.last)">Last</button>
</template>./ligne.vue