import { createRouter, createWebHistory, useRoute } from 'vue-router';

const routes = [
    {
        path: '/',
        name: 'main',
        component: () => import('@/components/Main.vue'),
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

router.beforeEach((to, from, next) => {
    next();
});

router.afterEach((to, from, failure) => {});

export default router;
