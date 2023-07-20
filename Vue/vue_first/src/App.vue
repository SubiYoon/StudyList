<script>
import HelloWorld from './components/HelloWorld.vue'
import TheWelcome from './components/TheWelcome.vue'
import Note from './components/Note.vue'
import WelcomeItem from "@/components/WelcomeItem.vue";
import HowToUseSlot from "@/components/HowToUseSlot.vue";

export default {
    components: {
        WelcomeItem,
        HelloWorld,
        TheWelcome,
        Note,
        HowToUseSlot,
    },
}
</script>

<template>
    <header>
        <img alt="Vue logo" class="logo" src="./assets/logo.svg" width="125" height="125"/>

        <div class="wrapper">
            <HelloWorld msg="You did it!"/>
        </div>
    </header>

    <main>
        <how-to-use-slot>
            <template v-slot:slot_start>시작이야</template>
            <template v-slot:slot_end>끝이야</template>
        </how-to-use-slot>
        <Note/>
        <TheWelcome/>
        <how-to-use-slot v-slot="test">
            {{test.count}} {{test.text}} {{test.text2}}
        </how-to-use-slot>
        <how-to-use-slot v-slot="{text, text2, x , y}">
            {{text}} {{text2}}
            <br>
            Mouse is at : {{x}}, {{y}}
        </how-to-use-slot>
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
