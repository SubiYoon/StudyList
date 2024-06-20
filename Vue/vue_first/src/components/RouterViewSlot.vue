<script setup>
import { onMounted, ref } from 'vue';
import Count from '@/components/Slot/Count.vue';
import NoCount from '@/components/Slot/NoCount.vue';
import { useRouter } from 'vue-router';

const component = ref(Count);
const changeComponent = (comp) => {
    component.value = comp;
};

const test = ref(null);
onMounted(() => {
    console.log(test.value);
});

const router = useRouter();
router.addRoute({ path: '/234', name: '234', component: NoCount });
router.removeRoute('234');
console.log(router.getRoutes());
</script>

<template>
    <br />
    <!--    <router-link to="#" @click="changeComponent(Count)">Count</router-link> |-->
    <!--    <router-link to="#" @click="changeComponent(NoCount)">No Count</router-link>-->
    <RouterLink to="/slot/count">Count</RouterLink> |
    <RouterLink to="/slot/nocount">NoCount</RouterLink>
    <router-view v-slot="{ Component, route }">
        <transition name="fade">
            <component :is="Component" ref="test" some-prop="a value" :key="route.path">
                <h1>테스트 중입니다.</h1>
            </component>
        </transition>
    </router-view>
</template>

<style scoped>
.fade-enter-active,
.fade-leave-active {
    transition: opacity 0.5s;
}

.fade-enter-to,
.fade-leave-to {
    opacity: 0;
}
</style>
