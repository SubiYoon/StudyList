import Main from '@/Main.vue';
import { createRouter, createWebHashHistory } from 'vue-router';

const routes = [
    { path: '/', name: 'Main', component: Main },
    { path: '/:id/:pwd', name: 'MainParam', component: Main },
    { path: '/Note', name: 'Note', component: () => import('@/components/Note.vue') },
    {
        path: '/router/:route',
        name: 'router',
        component: () => import('@/components/RouterStudy.vue'),
        children: [{ path: '', name: 'routerNote', component: () => import('@/components/Note.vue') }],
    },
];

const router = createRouter({
    history: createWebHashHistory('/'),
    routes,
});

export default router;
