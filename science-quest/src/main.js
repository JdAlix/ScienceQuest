import './assets/main.css'

import { createRouter, createWebHashHistory } from 'vue-router'
import { createApp } from 'vue'
import App from './App.vue'

//importer les components pour le routing
import PagePrincipale from "./components/PagePrincipale.vue"
import NotFound from "./components/erreurs/NotFound.vue"
import TestParametreURL from "./components/TestParametreURL.vue"

const routes = [
    { path: '/', component: PagePrincipale },
    { path: '/exemple/:id', component: TestParametreURL },
    { path: '/:pathMatch(.*)*', name: 'NotFound', component: NotFound },
]

const router = createRouter({
    // 4. Provide the history implementation to use. We
    // are using the hash history for simplicity here.
    history: createWebHashHistory(),
    routes, // short for `routes: routes`
})

createApp(App).use(router).mount('#app')
