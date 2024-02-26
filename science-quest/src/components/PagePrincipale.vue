<script>
import { NOM_APP } from "../assets/const";

export default{
    data(){
        return{
            nomApp:NOM_APP
        }
    }
}

</script>

<template>
    <p>Bienvenue dans {{ nomApp }} !! &lt;3</p>
</template>