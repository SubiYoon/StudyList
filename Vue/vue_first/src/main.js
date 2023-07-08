import './assets/main.css'

import { createApp } from 'vue'
import { createRouter, createWebHashHistory} from 'vue-router';
import Test from './components/Note.vue';

const routes = [
    { path: '/test', component : Test}
];

const router = createRouter({
    history: createWebHashHistory(),
    routes,
})

import App from './App.vue';

// App.use(router);

createApp(App).mount('#app')
