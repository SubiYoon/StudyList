<template>
    <img v-bind="imageAttrs" v-show="ok"/>
</template>

<script>
export default {
    name: "HowToUseBinding",
    data() {
        return {
            imageAttrs:
                {src: './icons/IconSupport.vue', alt: 'testIcon'},
            ok: ''
        }
    }
}
</script>

<style scoped>

</style>