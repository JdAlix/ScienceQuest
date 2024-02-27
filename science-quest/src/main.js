import './assets/main.css'

import { createRouter, createWebHashHistory } from 'vue-router'
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
import KahootVue from './components/Kahoot.vue'

const routes = [
    { path: '/', component: PagePrincipale },
    { path: '/login', component: Login },
    { path: '/kahoot', component: KahootVue}, //TODO: changer la route pour qu'elle soit trouvée automatiquement par le serveur (ce que demande l'utilisateur)
    { path: '/exemple/:id', component: TestParametreURL },
    { path: '/:pathMatch(.*)*', name: 'NotFound', component: NotFound },
]

const router = createRouter({
    history: createWebHashHistory(),
    routes, // short for `routes: routes`
})

createApp(App).use(router).mount('#app')
