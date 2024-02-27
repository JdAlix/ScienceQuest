<script>
export default {
    props: ["titreKahoot", "nbQuestions", "createur"]
}
</script>


<template>
    <div class="Kahoot-List-Item">
        <h2>{{ titreKahoot }}</h2>
        <p>{{ nbQuestions }}</p>
        <p>{{ createur }}</p>
        <button class="btn btn-dark">Jouer</button>
    </div>
</template>