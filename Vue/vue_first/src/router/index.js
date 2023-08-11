import App from "@/Main.vue";
import Note from "@/components/Note.vue";
import {createRouter, createWebHashHistory} from "vue-router";
import Main from "@/Main.vue";

const routes = [
    { path: '/', name: 'Main', component: Main},
    { path: '/Note', name: 'Note', component : Note}
];

const router = createRouter({
    history: createWebHashHistory('/'),
    routes,
})

export default router