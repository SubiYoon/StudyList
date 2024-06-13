<script setup>
import { useRoute } from 'vue-router';

const route = useRoute();
const props = defineProps(['routeObject', 'route', 'functionData']);

console.log(route.params);
console.log(props.routeObject);
console.log(props.route);
console.log(props.functionData);
</script>

<template>
    <h1>라우터 공부방</h1>
    <div v-for="item in route.params.route">
        <span>{{ item }}</span>
    </div>
    <router-link to="/">테스트</router-link>
    <router-view />
</template>

<style scoped></style>
