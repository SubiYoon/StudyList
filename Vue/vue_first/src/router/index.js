import { createRouter, createWebHistory, useRoute } from 'vue-router';
import Count from '@/components/Slot/Count.vue';
import NoCount from '@/components/Slot/NoCount.vue';

const routes = [
    {
        path: '/',
        name: 'main',
        component: () => import('@/components/Main.vue'),
    },
    {
        path: '/guide',
        name: 'guide',
        component: () => import('@/components/Guide.vue'),
    },
    {
        path: '/slot',
        name: 'slot',
        props: true,
        component: () => import('@/components/RouterViewSlot.vue'),
        children: [
            {
                path: 'count',
                name: 'count',
                props: true,
                component: Count,
            },
            {
                path: 'nocount',
                name: 'nocount',
                props: true,
                component: NoCount,
            },
        ],
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
    linkActiveClass: 'router-active',
});

router.beforeEach((to, from, next) => {
    next();
});

router.afterEach((to, from, failure) => {});

export default router;
