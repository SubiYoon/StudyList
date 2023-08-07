<script setup>
import { defineExpose } from 'vue';

        const message = 'Hello, World!';

        const hello = () => {
            console.log('Hello from child component!');
        };

        defineExpose({
            message,
            hello
        });
</script>

<template>
    <div>
        {{ message }}
    </div>
</template>

<style scoped>

</style>