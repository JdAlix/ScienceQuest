<script>
export default{
    props:["vieRestantes"], //maximum 10
    data(){
        return {
            ordreDessin:[
                this.potenceVertical,
                this.potencePoutre,
                this.potenceEquerre,
                this.corde,
                this.tete,
                this.corps,
                this.brasGauche,
                this.brasDroit,
                this.jambeDroite,
                this.jambeGauche
            ],
            viesEpuisees:0,
            ctx:null
        }
    },
    mounted(){
        this.$refs.pendu.width=200
        this.$refs.pendu.height=200
        this.ctx = this.$refs.pendu.getContext("2d");
        this.viesEpuisees=this.ordreDessin.length-(this.vieRestantes??this.ordreDessin.length)
    },
    methods:{
        dessiner(){
            if(this.viesEpuisees<this.ordreDessin.length){
                this.ordreDessin[this.viesEpuisees]()
                this.viesEpuisees++;
            }
        },
        potenceBas: function(){
            // Tracer la potence (trait bas)
            this.ctx.beginPath();
            this.ctx.moveTo(50, 200);
            this.ctx.lineTo(150, 200);
            this.ctx.stroke();
        },
        potenceVertical: function(){
            this.potenceBas()
            // Tracer la potence (trait vertical)
            this.ctx.beginPath();
            this.ctx.moveTo(100, 200);
            this.ctx.lineTo(100, 50);
            this.ctx.stroke();
        },
        potencePoutre: function(){
            // Tracer la potence (poutre)
            this.ctx.beginPath();
            this.ctx.moveTo(100, 50);
            this.ctx.lineTo(150, 50);
            this.ctx.stroke();
        },
        potenceEquerre: function(){
            // Tracer la potence (equerre)
            this.ctx.beginPath();
            this.ctx.moveTo(100, 70);
            this.ctx.lineTo(130, 50);
            this.ctx.stroke();
        },
        corde: function(){
            // Tracer la corde
            this.ctx.beginPath();
            this.ctx.moveTo(150, 50); // Déplacer le point de départ au sommet de la potence
            this.ctx.lineTo(150, 75); // Tracer une ligne verticale jusqu'à la tête du pendu
            this.ctx.stroke(); // Dessiner le trait
        },
        tete: function(){
            // Tracer la tête
            this.ctx.beginPath();
            this.ctx.arc(150, 87, 12, 0, Math.PI * 2);
            this.ctx.stroke();
        },
        corps: function(){
            // Tracer le corps
            this.ctx.beginPath();
            this.ctx.moveTo(150, 100);
            this.ctx.lineTo(150, 150);
            this.ctx.stroke();
        },
        brasGauche: function(){
            // Tracer le bras gauche
            this.ctx.beginPath();
            this.ctx.moveTo(150, 110);
            this.ctx.lineTo(130, 130);
            this.ctx.stroke();
        },
        brasDroit: function(){
            // Tracer le bras droit
            this.ctx.beginPath();
            this.ctx.moveTo(150, 110);
            this.ctx.lineTo(170, 130);
            this.ctx.stroke();
        },
        jambeGauche: function(){
            // Tracer la jambe gauche
            this.ctx.beginPath();
            this.ctx.moveTo(150, 150);
            this.ctx.lineTo(130, 180);
            this.ctx.stroke();
        },
        jambeDroite: function(){
            // Tracer la jambe droite
            this.ctx.beginPath();
            this.ctx.moveTo(150, 150);
            this.ctx.lineTo(170, 180);
            this.ctx.stroke();
        }
    }
}
</script>


<template>
    <button @click="dessiner">zéearezerzergfsdfg</button>
    <canvas ref="pendu"></canvas>
</template>