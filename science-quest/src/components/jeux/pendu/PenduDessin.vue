<script>
export default{
    props:["vieRestantes"], //maximum 10
    data(){
        return {

        }
    },
    methods:{
        dessiner: function(){
            // Récupérer le contexte de dessin 2D
            this.$refs.pendu.width=200
            this.$refs.pendu.height=200
            
const ctx = this.$refs.pendu.getContext("2d");

// Tracer la potence (trait bas)
ctx.beginPath();
ctx.moveTo(50, 200);
ctx.lineTo(150, 200);
ctx.stroke();

// Tracer la potence (trait vertical)
ctx.beginPath();
ctx.moveTo(100, 200);
ctx.lineTo(100, 50);
ctx.stroke();

// Tracer la potence (poutre)
ctx.beginPath();
ctx.moveTo(100, 50);
ctx.lineTo(150, 50);
ctx.stroke();

// Tracer la potence (equerre)
ctx.beginPath();
ctx.moveTo(100, 70);
ctx.lineTo(130, 50);
ctx.stroke();

// Tracer la corde
ctx.beginPath();
ctx.moveTo(150, 50); // Déplacer le point de départ au sommet de la potence
ctx.lineTo(150, 75); // Tracer une ligne verticale jusqu'à la tête du pendu
ctx.stroke(); // Dessiner le trait

// Tracer l'équerre
ctx.beginPath();
ctx.moveTo(150, 100);
ctx.lineTo(150, 150);
ctx.stroke();

// Tracer la tête
ctx.beginPath();
ctx.arc(150, 87, 12, 0, Math.PI * 2);
ctx.stroke();

// Tracer le corps
ctx.beginPath();
ctx.moveTo(150, 100);
ctx.lineTo(150, 150);
ctx.stroke();

// Tracer les bras
ctx.beginPath();
ctx.moveTo(150, 110);
ctx.lineTo(130, 130);
ctx.moveTo(150, 110);
ctx.lineTo(170, 130);
ctx.stroke();

// Tracer les jambes
ctx.beginPath();
ctx.moveTo(150, 150);
ctx.lineTo(130, 180);
ctx.moveTo(150, 150);
ctx.lineTo(170, 180);
ctx.stroke();
        }
    }
}
</script>


<template>
    <button @click="dessiner">zéearezerzergfsdfg</button>
    <canvas ref="pendu"></canvas>
</template>