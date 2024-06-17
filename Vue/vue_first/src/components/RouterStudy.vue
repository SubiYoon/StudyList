<script setup>
import { onBeforeRouteLeave, useRoute, useRouter } from 'vue-router';

const route = useRoute();
const router = useRouter();
const props = defineProps(['routeObject', 'route', 'functionData']);

console.log(route.params);
console.log(props.routeObject);
console.log(props.route);
console.log(props.functionData);

const consoleLog = (item) => {
    console.log(item);
};

consoleLog(route.meta);

onBeforeRouteLeave((to, from, next) => {
    if (confirm('나가냐?')) {
        next();
    } else return false;
});
</script>

<template>
    <h1>라우터 공부방</h1>
    <div v-for="item in route.params.route">
        <span>{{ item }}</span>
    </div>
    <router-link to="/">테스트</router-link>
    <router-view />
    <router-view name="test1" />
</template>

<style scoped></style>
