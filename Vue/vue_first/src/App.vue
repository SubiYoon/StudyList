<script setup></script>

<template>
    <router-link to="/">Home</router-link> | <router-link to="/guide">Guide</router-link> |
    <router-link to="/slot">Slot</router-link>
    <br />
    <router-view v-slot="{ Component }">
        <component :is="Component" />
    </router-view>
</template>

<style scoped>
.router-active {
    border-bottom: 1px solid white;
}
</style>
