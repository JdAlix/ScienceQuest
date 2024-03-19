<script>

export default {
    data() {
        return {
			codePartie: this.$route.params.code ?? -1
		}
	}
}

</script>


<template>
	<p>debug : code partie {{ codePartie }}</p>
</template>