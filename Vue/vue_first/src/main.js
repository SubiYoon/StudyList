import './assets/main.css';

import { createApp } from 'vue';
// import { createRouter, createWebHashHistory} from 'vue-router';
// import Note from './components/Note.vue';
import router from '@/router';
import App from '@/App.vue';

const app = createApp(App);

app.use(router);

app.mount('#app');
