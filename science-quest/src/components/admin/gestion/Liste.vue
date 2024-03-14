<script>
import { REST_API } from '@/assets/const';

import LigneDonnee from './ListeLigne.vue';

export default{
    data() {
        return {
            endpoint:this.$route.query.endpoint ?? "thematiques", //endpoint de l'api a recuperer
            //données obtenues par l'api
            donnees: [],
            page:0,
            REST_API:REST_API,

            //HATEOAS
            self:"",
            first:null, // a prendre a partir de la requete
            prev:null,
            next:null,
            last:null,
        };
    },
    mounted(){
        this.rafraichirEndpoint()
    },
    methods:{
        rafraichirEndpoint(){
            this.self=`${REST_API}/${this.endpoint}?page=${this.page}`
            this.getdonnees(this.self)
        },
        getdonnees(url){
            //HACK : s'assurer que les liens sont en HTTPS
            url=url.replace("http://", "https://")
            //enlever les anciens du tableau
            this.donnees=[]
            //TODO : ajouter un delai si jamais la requete est trop rapide pour VueJS
            //appeler l'API
            fetch(url).then(response=>{
                response.json().then(json=>{
                    const oldLength=this.donnees.length
                    //prendre les donnees de la requete
                    this.donnees.push(...json._embedded)

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
    components: { LigneDonnee }
}
</script>


<template>
    <!-- TODO : remplacer input par select ?-->
    <label for="endpoint">Endpoint API (REST) {{ REST_API }}/</label>
    <input v-model="endpoint" id="endpoint">
    <button @click="rafraichirEndpoint()">Rafraichir</button>
    <!-- TODO : popup qui affiche Ajout.vue pour ajouter une nouvelle ligne-->
    <table class="table">
        <thead>
    <tr>
        <th scope="col" v-for="nomColonne in Object.keys(donnees[0]??{})">{{nomColonne}}</th>
        <th scope="col">Actions</th>
    </tr>
  </thead>
  <tbody>
    <LigneDonnee v-for="champ in donnees"
    :champs-init="champ"
    ></LigneDonnee>
</tbody>
    </table>
    <button :class="{ invisible: !first }" class="btn btn-secondary" @click="this.getdonnees(this.first)">First</button>
    <button :class="{ invisible: !prev }"  class="btn btn-secondary" @click="this.getdonnees(this.prev)">Prev</button>
    <button :class="{ invisible: !next }"  class="btn btn-secondary" @click="this.getdonnees(this.next)">Next</button>
    <button :class="{ invisible: !last }"  class="btn btn-secondary" @click="this.getdonnees(this.last)">Last</button>
</template>