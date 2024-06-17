import Main from '@/Main.vue';
import { createRouter, createWebHashHistory, createWebHistory, onBeforeRouteLeave, useRoute } from 'vue-router';
import Note from '@/components/Note.vue';
import HowToUseLoopIs from '@/components/HowToUseLoopIs.vue';
import { devtools } from 'vue';

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
        meta: { test1: true },
        component: () => import('@/components/RouterStudy.vue'),
        children: [
            {
                path: '',
                name: 'test1',
                meta: { test2: false },
                components: {
                    default: HowToUseLoopIs,
                    test1: Note,
                },
                props: true,
            },
        ],
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

router.beforeEach((to, from, next) => {
    next();
});

router.afterEach((to, from, failure) => {
    let route = useRoute();
    console.log(route.meta);
    console.log(to);
    console.log(from);
    console.log(failure);
});

export default router;
