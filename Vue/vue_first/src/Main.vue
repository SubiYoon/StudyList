<script setup>
import TheWelcome from './components/TheWelcome.vue';
import HowToUseSlot from '@/components/HowToUseSlot.vue';
import HowToUseBinding from '@/components/HowToUseBinding.vue';
import { provide, reactive } from 'vue';
import router from '@/router';

provide('App', "Hello, I'm App!!");

const goRouter = () => {
    router.push({ name: 'router', params: { route: '테스트' } });
};
</script>

<template>
    <div>
        <nav>
            <router-link to="/">홈으로 가자</router-link> | <router-link to="/Note">노트로 가자</router-link> |
            <router-link :to="`/router/라우트`">라우터 공부하러 가자</router-link> |
            <router-link :to="{ name: 'router2', query: { test: '사과돌려깍기' } }">라우터 공부하러 가자 함수 모드</router-link> |
            <a @click="goRouter">라우터 공부하러 가자2</a>
        </nav>
    </div>

    <br />
    <div>{{ $route.params }}</div>
    <div>{{ $route.query }}</div>
    <div>{{ $route.hash }}</div>
    <br />

    <main>
        <how-to-use-slot>
            <template v-slot:slot_start>시작이야</template>
            <template v-slot:slot_end>끝이야</template>
        </how-to-use-slot>
        <TheWelcome />
        <how-to-use-slot v-slot="test"> {{ test.count }} {{ test.text }} {{ test.text2 }} </how-to-use-slot>
        <how-to-use-slot v-slot="{ text, text2, x, y }">
            {{ text }} {{ text2 }}
            <br />
            Mouse is at : {{ x }}, {{ y }}
        </how-to-use-slot>
        <how-to-use-binding></how-to-use-binding>
    </main>
</template>

<style scoped></style>
