<script setup>
import { ref, defineProps } from 'vue';

const props = defineProps(['someProp', 'a']);
console.log(props['someProp']);
console.log(props['a']);
const count = ref(0);
const countUp = () => {
    count.value++;
};
</script>

<template>
    <br />
    <button @click="countUp">Count up</button>
    <slot></slot>
    <h1>Count = {{ count }}</h1>
</template>

<style scoped></style>
