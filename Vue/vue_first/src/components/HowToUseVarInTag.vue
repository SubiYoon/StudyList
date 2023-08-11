<script setup>
    import {ref} from "vue";
    import HowToUseDefineExpose from "@/components/HowToUseDefineExpose.vue";

    const vMyDirective = {
        beforeMount: (el) => {
            console.log('우워어어어')
        }
    }

    const sample = ref('Foo')

    defineOptions({
        nameFoo: 'FOO',
        abcd: false,
        conWow: () => {
            console.log('Wow!!!!!')
        }
    })
</script>

<template>
    <h1 v-my-directive>다이렉트???</h1>
    <how-to-use-define-expose ref="test"></how-to-use-define-expose>
<!--<h1>{{ $refs.test.message }}</h1>-->
</template>

<style scoped>

</style>