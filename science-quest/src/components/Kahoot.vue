<script> //TODO définir les méthodes -> à définir grâce à l'API
export default {
    data() {
        return {
        }
    },
    methods:{ 
        
    }
}
</script>

<style lang="scss">
@import "../scss/kahoot.scss";
</style>

<template>
    <div class="Kahoot">
        <h1 style="padding-left: 0.5em;">Kahoot</h1>
        <div class="Kahoot-Header">
            <h2>Rejoindre un Kahoot</h2>
            <form>
                <label for="Kahoot-Code">Code</label>
                <input type="text" id="Kahoot-Code" name="Kahoot-Code">
                <button class="Kahoot-Join-Button">Rejoindre</button>
            </form>
        </div>
        <div class="Kahoot-content">
            <div class="Kahoot-Create">
                <h2>Créer un Kahoot</h2> <!-- TODO : Il faut changer le formulaire pour juste cliquer sur un bouton et que ça affiche une pop-up -->
                <form>
                    <label for="Kahoot-Create-Title">Titre</label>
                    <input type="text" id="Kahoot-Create-Title" name="Kahoot-Create-Title">
                    <label for="Kahoot-Create-Questions">Nombre de questions</label>
                    <input type="number" id="Kahoot-Create-Questions" name="Kahoot-Create-Questions">
                    <button class="Kahoot-Create-Button">Créer</button>
                </form>
            </div>
            <div class="Kahoot-List">
                <h2> Vos Quizz</h2>
                <div class="Kahoot-List-Item">
                    <h2>Titre du Kahoot</h2>
                    <p>Nombre de questions : 10</p>
                    <p>Créé par : Professeur X</p>
                    <button class="Kahoot-List-Item-Button">Jouer</button>
                </div>
                <div class="Kahoot-List-Item">
                    <h2>Titre du Kahoot</h2>
                    <p>Nombre de questions : 10</p>
                    <p>Créé par : Professeur X</p>
                    <button class="Kahoot-List-Item-Button">Jouer</button>
                </div>
                <div class="Kahoot-List-Item">
                    <h2>Titre du Kahoot</h2>
                    <p>Nombre de questions : 10</p>
                    <p>Créé par : Professeur X</p>
                    <button class="Kahoot-List-Item-Button">Jouer</button>
                </div>
            </div>
        </div>
    </div>
</template>
