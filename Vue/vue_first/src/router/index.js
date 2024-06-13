import Main from '@/Main.vue';
import { createRouter, createWebHashHistory, createWebHistory } from 'vue-router';

const routes = [
    { path: '/', name: 'Main', component: Main },
    { path: '/:id/:pwd', name: 'MainParam', component: Main },
    { path: '/Note', name: 'Note', component: () => import('@/components/Note.vue') },
    {
        path: '/router/:route',
        name: 'router',
        props: {
            routeObject: {
                a: 0,
                b: '',
                c: true,
            },
            route: true,
        },
        component: () => import('@/components/RouterStudy.vue'),
        children: [{ path: '', name: 'routerNote', component: () => import('@/components/Note.vue'), props: true }],
    },
    {
        path: '/router2',
        name: 'router2',
        props: (route) => ({ functionData: route.query.test }),
        component: () => import('@/components/RouterStudy.vue'),
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
