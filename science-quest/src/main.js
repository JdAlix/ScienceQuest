import './assets/main.css'

import { createRouter, createWebHistory } from 'vue-router'
import { createApp } from 'vue'
import App from './App.vue'

//importer bootstrap
import * as bootstrap from 'bootstrap'
import './scss/styles.scss'

//importer les components pour le routing
import PagePrincipale from "./components/PagePrincipale.vue"
import NotFound from "./components/erreurs/NotFound.vue"
import TestParametreURL from "./components/TestParametreURL.vue"
import Login from "./components/Login.vue"
import KahootVue from './components/jeux/kahoot/Kahoot.vue'
import KahootPartie from './components/jeux/kahoot/KahootPartie.vue'
import Pendu from './components/jeux/pendu/Pendu.vue'
import AdminGestionDonnees from "./components/admin/gestion/Liste.vue"
import Inscription from './components/Inscription.vue'

const routes = [
    { path: '/', component: PagePrincipale },
    { path: '/login', component: Login },
    { path: '/inscription', component: Inscription },
    { path: '/kahoot', component: KahootVue}, //TODO: changer la route pour qu'elle soit trouvée automatiquement par le serveur (ce que demande l'utilisateur)
    { path: '/kahoot/partie/:code', component: KahootPartie},
    { path: '/pendu', component: Pendu },
    { path: '/exemple/:id', component: TestParametreURL },
    { path: '/admin/gestion', component: AdminGestionDonnees },
    { path: '/:pathMatch(.*)*', name: 'NotFound', component: NotFound },
]

const router = createRouter({
    history: createWebHistory(),
    routes, // short for `routes: routes`
})

createApp(App).use(router).mount('#app')
