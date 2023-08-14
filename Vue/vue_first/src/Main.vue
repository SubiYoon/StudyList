<script setup>
import TheWelcome from './components/TheWelcome.vue'
import HowToUseSlot from "@/components/HowToUseSlot.vue";
import HowToUseBinding from "@/components/HowToUseBinding.vue";
import {defineProps, provide} from "vue";

provide('App', "Hello, I'm App!!")

const props = defineProps(['id'])

alert(props.id)
</script>

<template>
    <router-link to="/">홈으로 가자</router-link>
    <router-link to="/Note">노트로 가자</router-link>

    <br>
    <div>{{ $route.params }}</div>
    <div>{{ $route.query }}</div>
    <div>{{ $route.hash }}</div>
    <br>

    <main>
        <how-to-use-slot>
            <template v-slot:slot_start>시작이야</template>
            <template v-slot:slot_end>끝이야</template>
        </how-to-use-slot>
        <TheWelcome/>
        <how-to-use-slot v-slot="test">
            {{ test.count }} {{ test.text }} {{ test.text2 }}
        </how-to-use-slot>
        <how-to-use-slot v-slot="{text, text2, x , y}">
            {{ text }} {{ text2 }}
            <br>
            Mouse is at : {{ x }}, {{ y }}
        </how-to-use-slot>
        <how-to-use-binding></how-to-use-binding>
    </main>
</template>

<style scoped>
header {
    line-height: 1.5;
}

.logo {
    display: block;
    margin: 0 auto 2rem;
}

@media (min-width: 1024px) {
    header {
        display: flex;
        place-items: center;
        padding-right: calc(var(--section-gap) / 2);
    }

    .logo {
        margin: 0 2rem 0 0;
    }

    header .wrapper {
        display: flex;
        place-items: flex-start;
        flex-wrap: wrap;
    }
}
</style>
