<script setup>
import { onMounted, ref } from 'vue';
import Count from '@/components/Slot/Count.vue';
import NoCount from '@/components/Slot/NoCount.vue';

const component = ref(Count);
const changeComponent = (comp) => {
    component.value = comp;
};

const test = ref(null);
onMounted(() => {
    console.log(test.value);
});
</script>

<template>
    <br />
    <!--    <router-link to="#" @click="changeComponent(Count)">Count</router-link> |-->
    <!--    <router-link to="#" @click="changeComponent(NoCount)">No Count</router-link>-->
    <RouterLink to="/slot/count">Count</RouterLink> |
    <RouterLink to="/slot/nocount">NoCount</RouterLink>
    <router-view v-slot="{ Component }">
        <transition name="fade">
            <component :is="Component" ref="test" some-prop="a value">
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
