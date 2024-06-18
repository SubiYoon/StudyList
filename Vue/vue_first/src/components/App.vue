<script setup></script>

<template>
    <router-link to="/">홈으로</router-link>
    <router-view v-slot="{ Component }">
        <transition name="fade">
            <component :is="Component" />
        </transition>
    </router-view>
</template>

<style scoped></style>
