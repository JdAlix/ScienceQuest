<script>
export default{
    methods:{
        envoyerDonnees: function(event){
            const donnees=new FormData(document.querySelector("#formajouterscientifiques"))

            //todo mettre lien dans const
            fetch("localhost/api/v1/scientifiques", {method:"POST", body:JSON.stringify(Object.fromEntries(donnees))})
            //sans le JSON.stringify et Object.fromEntries ca fait une requete en Content-Disposition
        }
    }
}


//TODO: verifier si on est admin quand on entre dans la partie admin
</script>

<template>
<form id="formajouterscientifiques" @submit.prevent>
    <div>
        <label for="nom">Nom</label>
        <input class="form-control" type="text" id="nom" name="nom"/>
    </div>
    <div>
        <label for="prenom">Prénom</label>
        <input class="form-control" type="text" id="prenom" name="prenom"/>
    </div>
    <div>
        <label for="date">Date de naissance</label>
        <input class="form-control" type="date" id="date" name="date"/>
    </div>
    <div>
        <label for="desc">Descriptif</label>
        <textarea class="form-control" id="descriptif" name="descriptif"></textarea>
    </div>
    <div>
        <label for="sexe">Sexe</label>
        <select class="form-select" id="sexe" name="sexe">
            <option selected value="F">Femme</option>
            <option value="H">Homme</option>
        </select>
    </div>
    
    <button v-on:click="envoyerDonnees" class="btn btn-primary">Ajouter</button>
</form>
</template>