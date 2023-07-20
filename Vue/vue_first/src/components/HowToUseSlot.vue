<template>
  <slot name="slot_start"></slot>
  <h1>Slot 위아래 기준 나누기</h1>
  <slot name="slot_end"></slot>
  <slot :text="Hello" :count="123" :text2="World"></slot>
  <slot :x="x" :y="y"/>
</template>

<script>
export default {
    name: "HowToUseSlot",
    data() {
        return {
            Hello : 'Hello',
            World : 'World',
            x : 0,
            y : 0,
        }
    },
    methods: {
        update(e){
            this.x = e.pageX
            this.y = e.pageY
        }
    },
    mounted() {
        window.addEventListener('mousemove', this.update)
    },
    unmounted() {
        window.removeEventListener('mousemove', this.update)
    }
}
</script>

<style scoped>

</style>